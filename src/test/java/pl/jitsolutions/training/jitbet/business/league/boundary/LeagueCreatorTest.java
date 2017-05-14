package pl.jitsolutions.training.jitbet.business.league.boundary;

import java.time.LocalDate;
import java.time.Month;
import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.jitsolutions.training.jitbet.business.game.entity.Game;
import pl.jitsolutions.training.jitbet.business.league.entity.League;
import pl.jitsolutions.training.jitbet.business.league.entity.Sponsor;
import pl.jitsolutions.training.jitbet.business.round.entity.Round;
import pl.jitsolutions.training.jitbet.business.team.entity.Team;

@RunWith(Arquillian.class)
public class LeagueCreatorTest {

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
                .addClass(LeagueCreator.class)
                .addClass(League.class)
                .addClass(Round.class)
                .addClass(Team.class)
                .addClass(Game.class)
                .addAsResource("test-persistence.xml", "/META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        archive.writeTo(System.out, Formatters.VERBOSE);

        return archive;
    }

    @EJB
    private LeagueCreator leagueCreator;

    @Transactional(TransactionMode.ROLLBACK)
    @Test
    public void create() {
        League league = new League();
        league.setName("Premier League");
        league.setSponsor(Sponsor.ORLEN);
        league.setNumberOfTeams(20);
        league.setEstablished(LocalDate.of(1991, Month.JULY, 1));

        leagueCreator.create(league);
    }
}