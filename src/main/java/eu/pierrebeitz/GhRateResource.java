package eu.pierrebeitz;

import org.kohsuke.github.GitHub;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/rate")
public class GhRateResource {

    @Inject
    GitHub gitHub;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public int rate() throws IOException {
        return gitHub.getRateLimit().getLimit();
    }
}