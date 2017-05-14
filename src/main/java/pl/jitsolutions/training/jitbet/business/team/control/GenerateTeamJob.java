package pl.jitsolutions.training.jitbet.business.team.control;

import java.time.Year;
import java.util.concurrent.Callable;
import javax.ejb.EJB;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jitsolutions.training.jitbet.business.team.entity.Team;

public class GenerateTeamJob implements Callable<Void> {

    private static final Logger LOG = LoggerFactory.getLogger(GenerateTeamJob.class);

    @EJB
    private JmsSender jmsSender;


    @Override
    public Void call() throws Exception {
        LOG.info("Processing generate team job...");

        Team team = new Team();
        team.setName(RandomStringUtils.randomAlphabetic(16));
        team.setEstablished(Year.of(RandomUtils.nextInt(1900, 2000)));

        jmsSender.send(team);

        return null;
    }
}
