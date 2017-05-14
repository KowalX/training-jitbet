package pl.jitsolutions.training.jitbet.presentation.league;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jitsolutions.training.jitbet.business.league.boundary.LeagueCreator;
import pl.jitsolutions.training.jitbet.business.league.entity.League;
import pl.jitsolutions.training.jitbet.business.league.entity.Sponsor;

@Named
@ViewScoped
@URLMapping(id = "addLeague", pattern = "/leagues/add/", viewId = "/addLeague.xhtml")
public class AddLeaguePresenter implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(AddLeaguePresenter.class);

    @EJB
    private LeagueCreator leagueCreator;

    private League league = new League();

    public void save() {
        LOG.info("Saving new league = {}.", league);

        leagueCreator.create(league);

        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "New league saved!", "");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public List<Sponsor> getSponsors() {
        return Arrays.stream(Sponsor.values()).collect(Collectors.toList());
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
}
