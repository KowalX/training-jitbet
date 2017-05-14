package pl.jitsolutions.training.jitbet.business.team.boundary;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jitsolutions.training.jitbet.business.team.control.GenerateTeamJob;
import pl.jitsolutions.training.jitbet.business.team.control.TeamsGenerator;

@Path("teamsGenerator")
@Stateless
public class TeamsGeneratorResource {

    private static final Logger LOG = LoggerFactory.getLogger(TeamsGeneratorResource.class);

    @Resource
    private ManagedExecutorService executorService;

    @Inject
    private Instance<GenerateTeamJob> generateTeamJobFactory;

    @Inject
    private Event<GenerateTeamJob> generateTeamJobEvent;

    @EJB
    private TeamsGenerator teamsGenerator;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("generateTeams")
    public String generateTeams() {

        List<GenerateTeamJob> jobs = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            GenerateTeamJob generateTeamJob = generateTeamJobFactory.get();

            LOG.info("Adding new job = {}.", i);

            jobs.add(generateTeamJob);
        }

        try {
            executorService.invokeAll(jobs);
        } catch (InterruptedException e) {
            LOG.error("Unable to invoke all jobs...", e);
        }

        return "generated!";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("generateTeamsCDI")
    public String generateTeamsCDI() {

        for (int i = 0; i < 10000; i++) {
            GenerateTeamJob generateTeamJob = generateTeamJobFactory.get();

            LOG.info("Adding new job = {}.", i);

            generateTeamJobEvent.fire(generateTeamJob);
        }

        return "generated!";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("generateTeamsAsync")
    public String generateTeamsAsyn() {

        AsyncResult<String> asyncResult = teamsGenerator.generate();

        return "generated!";
    }


}
