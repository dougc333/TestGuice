package jerseyserver;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.palominolabs.config.ConfigModule;
import com.palominolabs.config.ConfigModuleBuilder;
import org.apache.commons.configuration.SystemConfiguration;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Created by dc on 4/12/17.
 */
public class JerseyModule extends AbstractModule{
    public void configure(){
        binder().requireExplicitBindings();
        binder().requireAtInjectOnConstructors();
        binder().requireExactBindingAnnotations();

        bind(Main.class);
        bind(ControllerJobResource.class);

        install(new ConfigModuleBuilder().addConfiguration(new SystemConfiguration()).build());
        ConfigModule.bindConfigBean(binder(), JerseyServerConfig.class);
        install(new IpcJsonModule());
        install(new JerseySupportModule());
        bind(ControllerJerseyApp.class); //

    }

    //use a servletcontainer vs servlet in simplewebservice
    @Singleton
    @Provides
    ServletContainer getServletContainer(ControllerJerseyApp app) {
        return new ServletContainer(app);
    }
}
