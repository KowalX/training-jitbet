package pl.jitsolutions.training.jitbet.business.team.control;

import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TeamGeneratorConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(TeamGeneratorConsumer.class);

    public void generate(@Observes GenerateTeamJob job) {
        LOG.info("Generating team....");

        try {
            job.call();
        } catch (Exception e) {
            LOG.error("Unable to generate...", e);
        }
    }
}
