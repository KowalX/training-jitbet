package pl.jitsolutions.training.jitbet.presentation.round;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import pl.jitsolutions.training.jitbet.business.account.boundary.LoggedAccount;
import pl.jitsolutions.training.jitbet.business.account.entity.Account;
import pl.jitsolutions.training.jitbet.business.league.boundary.LeagueCreator;
import pl.jitsolutions.training.jitbet.business.league.entity.League;
import pl.jitsolutions.training.jitbet.business.league.entity.Sponsor;
import pl.jitsolutions.training.jitbet.business.round.boundary.CompletedBySponsor;
import pl.jitsolutions.training.jitbet.business.round.boundary.CompletedRounds;
import pl.jitsolutions.training.jitbet.business.round.entity.Round;

@Named
@ApplicationScoped
@URLMapping(id = "rounds", pattern = "/rounds", viewId = "/rounds.xhtml")
public class RoundsPresenter {

    @Inject
    @LoggedAccount
    private Account account;

    @Inject
    @CompletedRounds
    @CompletedBySponsor(Sponsor.LOTTO)
    private List<Round> rounds;

    @EJB
    private LeagueCreator leagueCreator;

    @PostConstruct
    public void init() {
        League league = new League();
        league.setName("Nowa liga");

        leagueCreator.create(league);
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
