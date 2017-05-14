package pl.jitsolutions.training.jitbet.business;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jitsolutions.training.jitbet.business.league.entity.League;
import pl.jitsolutions.training.jitbet.business.league.entity.Sponsor;
import pl.jitsolutions.training.jitbet.business.round.entity.Round;
import pl.jitsolutions.training.jitbet.business.team.entity.Team;

@Singleton
@Startup
public class TestDataLoader {

    private static final Logger LOG = LoggerFactory.getLogger(TestDataLoader.class);

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        LOG.info("Init test data...");

        League league = initLeague();

        initRounds(league);

        initTeams();
    }

    private League initLeague() {
        League league = new League();
        league.setName("Ekstraklasa");
        league.setSponsor(Sponsor.LOTTO);
        league.setNumberOfTeams(20);
        league.setEstablished(LocalDate.of(1951, Month.JULY, 21));

        LOG.info("Saving league = {}.", league);

        entityManager.persist(league);

        return league;
    }

    private void initRounds(League league) {
        Round round1 = new Round();
        round1.setNumber(1);
        round1.setLeague(league);

        LOG.info("Saving round = {}.", round1);

        entityManager.persist(round1);

        Round round2 = new Round();
        round2.setNumber(2);
        round2.setLeague(league);

        LOG.info("Saving round = {}.", round2);

        entityManager.persist(round2);

        Round round3 = new Round();
        round3.setNumber(3);
        round3.setLeague(league);

        LOG.info("Saving round = {}.", round3);

        entityManager.persist(round3);

        Round round4 = new Round();
        round4.setNumber(4);
        round4.setLeague(league);

        LOG.info("Saving round = {}.", round4);

        entityManager.persist(round4);

        Round round5 = new Round();
        round5.setNumber(5);
        round5.setLeague(league);

        LOG.info("Saving round = {}.", round5);

        entityManager.persist(round5);

        Round round6 = new Round();
        round6.setNumber(6);
        round6.setLeague(league);

        LOG.info("Saving round = {}.", round6);

        entityManager.persist(round6);

        Round round7 = new Round();
        round7.setNumber(7);
        round7.setLeague(league);

        LOG.info("Saving round = {}.", round7);

        entityManager.persist(round7);

        Round round8 = new Round();
        round8.setNumber(8);
        round8.setLeague(league);

        LOG.info("Saving round = {}.", round8);

        entityManager.persist(round8);
    }

    private void initTeams() {
        Team team1 = new Team();
        team1.setName("Lechia Gdańsk");
        team1.setEstablished(Year.of(1976));

        entityManager.persist(team1);

        Team team2 = new Team();
        team2.setName("Legia Warszawa");
        team2.setEstablished(Year.of(1921));

        entityManager.persist(team2);

        Team team3 = new Team();
        team3.setName("Arka Gdynia");
        team3.setEstablished(Year.of(1811));

        entityManager.persist(team3);

        Team team4 = new Team();
        team4.setName("Lech Poznań");
        team4.setEstablished(Year.of(1999));

        entityManager.persist(team4);
    }
}
