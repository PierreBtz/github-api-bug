package eu.pierrebeitz;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import javax.enterprise.inject.Produces;
import java.io.IOException;

public class GhService {

    @ConfigProperty(name = "demo.github.pat")
    String token;

    @Produces
    public GitHub getGhClient() throws IOException {
        var gh = new GitHubBuilder()
              .withOAuthToken(token);
        return gh.build();
    }
}
