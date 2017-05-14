package pl.jitsolutions.training.jitbet.business.team.control;

import java.time.Year;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jitsolutions.training.jitbet.business.team.entity.Team;

@Stateless
public class TeamsGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(TeamsGenerator.class);

    @Inject
    private Instance<GenerateTeamJob> generateTeamJobFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Asynchronous
    public AsyncResult<String> generate() {
        for (int i = 0; i < 1000; i++) {
            Team team = new Team();
            team.setName(RandomStringUtils.randomAlphabetic(16));
            team.setEstablished(Year.of(RandomUtils.nextInt(1900, 2000)));

            entityManager.persist(team);

//            GenerateTeamJob generateTeamJob = generateTeamJobFactory.get();
//
//            LOG.info("Adding new job = {}.", i);
//
//            generateTeamJobEvent.fire(generateTeamJob);
        }

        return new AsyncResult<>("generated");

    }
}
