package pl.jitsolutions.training.jitbet.presentation.round;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import pl.jitsolutions.training.jitbet.business.league.entity.League;
import pl.jitsolutions.training.jitbet.business.league.entity.Sponsor;
import pl.jitsolutions.training.jitbet.business.round.entity.Round;

@Named
@RequestScoped
@URLMapping(id = "round", pattern = "/round/#{roundPresenter.roundNumber}/", viewId = "/round.xhtml")
public class RoundPresenter implements Serializable {

    private int roundNumber;

    private int i = 0;

    public Round getRound() {
        League league = new League();
        league.setName("Ekstraklasa");
        league.setNumberOfTeams(16);
        league.setSponsor(Sponsor.LOTTO);

        Round round = new Round();
        round.setNumber(1);
        round.setLeague(league);

        return round;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }
}
