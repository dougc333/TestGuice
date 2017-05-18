
import com.google.inject.AbstractModule;
import com.google.inject.servlet.ServletModule;



/**
 * Created by dc on 4/12/17.
 */
public class MyServletModule extends ServletModule {
    @Override
    protected void configureServlets(){
        serve("/guice").with(MyServlet.class);
    }
}
