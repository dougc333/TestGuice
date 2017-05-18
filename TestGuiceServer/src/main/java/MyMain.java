import com.google.inject.Guice;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * Created by dc on 4/12/17.
 * point browser to http:://localhost:8080/guice
 */
public class MyMain {
    public static void main(String[] args) throws Exception{
        Guice.createInjector(new MyServletModule());
        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler(server,"/",ServletContextHandler.SESSIONS);
        handler.addFilter(GuiceFilter.class,"/*", EnumSet.of(DispatcherType.REQUEST));
        handler.addServlet(DefaultServlet.class,"/");
        server.start();
        server.join();

    }
}
