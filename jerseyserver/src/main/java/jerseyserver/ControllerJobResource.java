package jerseyserver;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.MDC;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Path("/controller")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public final class ControllerJobResource {

    @Inject
    ControllerJobResource() {

        System.out.println("default controllerjobresource");
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response list() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>\n<body>\n");

        stringBuilder.append("<ul>\n");
        Set<UUID> jobIds = new HashSet<UUID>();
        jobIds.add((new UUID(0, 10)));
        for (UUID jobId : jobIds) {
            stringBuilder.append("<li><a href='/job/").append(jobId).append("'>").append(jobId).append("</a></li>\n");
        }
        stringBuilder.append("</ul>\n");

        stringBuilder.append("</html>");
        return Response.status(Response.Status.OK).entity(stringBuilder.toString()).build();
    }


    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {
        return "Test Test";
    }
}
