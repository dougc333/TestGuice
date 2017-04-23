package jerseyserver;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.palominolabs.http.server.HttpServerConnectorConfig;
import com.palominolabs.http.server.HttpServerWrapper;
import com.palominolabs.http.server.HttpServerWrapperConfig;
import com.palominolabs.http.server.HttpServerWrapperFactory;

/**
 * Created by dc on 4/12/17.
 */
public class Main {
    private HttpServerWrapperFactory httpServerFactory;
    //private JerseyServerConfig jerseyServerConfig;


    @Inject
    public Main(HttpServerWrapperFactory httpServerFactory){
        this.httpServerFactory = httpServerFactory;
        //this.jerseyServerConfig = jerseyServerConfig;
    }

    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new JerseyModule());
        injector.getInstance(Main.class).go();

    }

    public void go() throws Exception{
        HttpServerWrapperConfig config = new HttpServerWrapperConfig().withHttpServerConnectorConfig(
                new HttpServerConnectorConfig("localhost", 8080,false));

        HttpServerWrapper httpServer = httpServerFactory.getHttpServerWrapper(config);
        httpServer.start();

    }
}
