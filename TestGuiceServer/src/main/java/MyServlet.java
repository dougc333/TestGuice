import com.google.inject.Singleton;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dc on 4/12/17.
 */
@Singleton
public class MyServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws IOException {
        response.getWriter().println("asdfasdfsdfsad");
    }
}
