package pl.jitsolutions.training.jitbet.business.league.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.jitsolutions.training.jitbet.business.league.entity.League;

@Stateless
public class LeagueCreator {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(League league) {
        entityManager.persist(league);
    }
}
