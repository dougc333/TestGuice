package com.palominolabs.benchpress;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.inject.*;
import com.ning.http.client.AsyncHttpClient;
import com.palominolabs.benchpress.config.ZookeeperConfig;
import com.palominolabs.benchpress.controller.ControllerCoreModule;
import com.palominolabs.benchpress.controller.JobFarmer;
import com.palominolabs.benchpress.controller.http.ControllerJobResource;
import com.palominolabs.benchpress.controller.zookeeper.ZKServer;
import com.palominolabs.benchpress.controller.zookeeper.ZKServerModule;
import com.palominolabs.benchpress.curator.CuratorModule;
import com.palominolabs.benchpress.ipc.Ipc;
import com.palominolabs.benchpress.ipc.IpcHttpClientModule;
import com.palominolabs.benchpress.ipc.IpcJsonModule;
import com.palominolabs.benchpress.jersey.GuiceServiceLocatorGenerator;
import com.palominolabs.benchpress.jersey.JerseyResourceConfigBase;
import com.palominolabs.benchpress.jersey.JerseySupportModule;
import com.palominolabs.benchpress.jersey.ObjectMapperContextResolver;
import com.palominolabs.benchpress.job.registry.JobRegistryModule;
import com.palominolabs.benchpress.job.task.TaskPluginRegistryModule;
import com.palominolabs.benchpress.task.reporting.TaskProgressClientModule;
import com.palominolabs.benchpress.task.simplehttp.SimpleHttpTaskModule;
import com.palominolabs.benchpress.worker.*;
import com.palominolabs.benchpress.worker.http.WorkerControlResource;
import com.palominolabs.benchpress.worker.http.WorkerJobResource;
import com.palominolabs.benchpress.worker.http.WorkerResourceModule;
import com.palominolabs.config.ConfigModuleBuilder;
import com.palominolabs.http.server.HttpServerConnectorConfig;
import com.palominolabs.http.server.HttpServerWrapper;
import com.palominolabs.http.server.HttpServerWrapperConfig;
import com.palominolabs.http.server.HttpServerWrapperFactory;
import com.squarespace.jersey2.guice.JerseyGuiceUtils;
import org.apache.commons.configuration.MapConfiguration;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceInstance;
import org.eclipse.jetty.server.NetworkConnector;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.*;
import org.junit.rules.TemporaryFolder;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;

import static com.google.inject.Guice.createInjector;
import static org.junit.Assert.assertEquals;

/**
 * Created by dc on 5/13/17.
 */
public class TestMe {
    @Inject
    WorkerAdvertiser workerAdvertiser;
    @Inject
    HttpServerWrapperFactory httpServerFactory;
    @Inject
    ZKServer zkServer;
    @Inject
    CuratorModule.CuratorLifecycleHook curatorLifecycleHook;
    @Inject
    @Ipc
    AsyncHttpClient asyncHttpClient;
    @Inject
    ServiceDiscovery<WorkerMetadata> serviceDiscovery;
    @Inject
    ZookeeperConfig zookeeperConfig;
    @Inject
    WorkerControlFactory workerControlFactory;
    @Inject
    JobFarmer jobFarmer;
    @Ipc
    @Inject
    ObjectWriter objectWriter;
    @Ipc
    @Inject
    ObjectReader objectReader;
    @Inject
    SimpleHttpResource simpleHttpResource;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    ExecutorService executorService;
    HttpServerWrapper httpServer;
    private HttpServerWrapperConfig httpServerConfig;
    private String host;
    private int port;

    @BeforeClass
    public static void setUpClass() {
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("setUp");
        File zkTmpDir = temporaryFolder.newFolder();

        final Map<String, Object> configMap = new HashMap<>();
        configMap.put("benchpress.controller.zookeeper.embedded-server.tmp-dir", zkTmpDir.getAbsolutePath());

        Injector injector = createInjector(Stage.PRODUCTION, new AbstractModule() {
            @Override
            protected void configure() {
                binder().requireExplicitBindings();
                binder().requireAtInjectOnConstructors();
                binder().requireExactBindingAnnotations();

                install(new ConfigModuleBuilder().addConfiguration(new MapConfiguration(configMap))
                        .build());
                install(new JerseySupportModule());

                // basic zookeeper
                install(new ZKServerModule());
                install(new CuratorModule());

                // controller
                install(new IpcJsonModule());
                install(new ControllerCoreModule());

                // worker
                install(new JobRegistryModule());
                install(new TaskProgressClientModule());
                install(new IpcHttpClientModule());
                install(new TaskPluginRegistryModule());
                install(new WorkerResourceModule());
                install(new QueueProviderModule());
                bind(PartitionRunner.class);

                // custom task
                install(new SimpleHttpTaskModule());
                bind(SimpleHttpResource.class);
            }

            @Singleton
            @Provides
            ServletContainer getServletContainer(@Ipc ObjectMapperContextResolver objectMapperContextResolver) {
                return new ServletContainer(new TestMe.IntegrationTestJerseyApp(objectMapperContextResolver));
            }
        });
        JerseyGuiceUtils.install(new GuiceServiceLocatorGenerator(injector));

        injector.injectMembers(this);

        // Disable Zookeeper's annoying attempt to start its own out-of-date Jetty
        System.setProperty("zookeeper.admin.enableServer", "false");

        executorService = Executors.newCachedThreadPool();
        executorService.submit(zkServer);
        host = "localhost";
        httpServerConfig = new HttpServerWrapperConfig()
                .withHttpServerConnectorConfig(HttpServerConnectorConfig.forHttp(host, 0));
        httpServer = httpServerFactory.getHttpServerWrapper(this.httpServerConfig);
        httpServer.start();

        curatorLifecycleHook.start();

        NetworkConnector connector = (NetworkConnector) httpServer.getServer().getConnectors()[0];
        port = connector.getLocalPort();

        jobFarmer.setListenAddress("localhost");
        jobFarmer.setListenPort(port);
    }

    //
    private static class IntegrationTestJerseyApp extends JerseyResourceConfigBase {
        IntegrationTestJerseyApp(ObjectMapperContextResolver objectMapperContextResolver) {
            super(objectMapperContextResolver);

            register(ControllerJobResource.class);
            register(WorkerJobResource.class);
            register(WorkerControlResource.class);

            register(SimpleHttpResource.class);
        }
    }

    @After
    public void tearDown() throws Exception {
        curatorLifecycleHook.close();
        zkServer.close();
        executorService.shutdownNow();
        if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
            throw new RuntimeException("Executor did not shut down");
        }

        httpServer.stop();
        asyncHttpClient.close();
    }

    /**
     * @return the metadata loaded from ZK
     */
    private WorkerMetadata advertiseWorker() throws Exception {
        workerAdvertiser.initListenInfo(host, port);
        workerAdvertiser.advertiseAvailability();

        Collection<ServiceInstance<WorkerMetadata>> instances =
                serviceDiscovery.queryForInstances(zookeeperConfig.getWorkerServiceName());
        assertEquals(1, instances.size());

        WorkerMetadata workerMetadata = instances.iterator().next().getPayload();
        System.out.println("++++++++++zookeeper worker service name:"+zookeeperConfig.getWorkerServiceName());
        assertEquals(workerAdvertiser.getWorkerId(), workerMetadata.getWorkerId());
        System.out.println("worker Listen address:"+workerMetadata.getListenAddress());
        System.out.println("worker listen port:"+workerMetadata.getListenPort());
        System.out.println("workerAdvertiser workerId:"+workerMetadata.getWorkerId() );

        return workerMetadata;
    }

    @Test
    public void TestMe() throws Exception {
        System.out.println("advertise");
        WorkerMetadata workerMetadata = advertiseWorker();

    }


}
