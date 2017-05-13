package pl.jitsolutions.training.jitbet.business.round.boundary;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import pl.jitsolutions.training.jitbet.business.league.entity.League;
import pl.jitsolutions.training.jitbet.business.league.entity.Sponsor;
import pl.jitsolutions.training.jitbet.business.round.entity.Round;

//@RequestScoped
public class RoundsProducer {

    @Produces
    @CompletedRounds
    public List<Round> getRounds(InjectionPoint injectionPoint) {
        if (injectionPoint.getAnnotated().isAnnotationPresent(CompletedBySponsor.class)) {
            CompletedBySponsor completedBySponsor = injectionPoint.getAnnotated().getAnnotation(CompletedBySponsor.class);

            Sponsor value = completedBySponsor.value();
        }


        League league = new League();
        league.setName("Ekstraklasa");
        league.setNumberOfTeams(16);
        league.setSponsor(Sponsor.LOTTO);

        Round round1 = new Round();
        round1.setNumber(1);
        round1.setLeague(league);

        Round round2 = new Round();
        round2.setNumber(2);
        round2.setLeague(league);

        Round round3 = new Round();
        round3.setNumber(3);
        round3.setLeague(league);

        return Arrays.asList(round1, round2, round3);
    }
}
