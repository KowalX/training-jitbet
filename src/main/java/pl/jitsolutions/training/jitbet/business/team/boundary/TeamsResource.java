package pl.jitsolutions.training.jitbet.business.team.boundary;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import pl.jitsolutions.training.jitbet.business.team.control.A;
import pl.jitsolutions.training.jitbet.business.team.control.B;
import pl.jitsolutions.training.jitbet.business.team.entity.Team;
import pl.jitsolutions.training.jitbet.business.team.entity.TeamNotFoundException;

@Path("teams")
@Stateless
public class TeamsResource {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private A a;

    @EJB
    private B b;

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext jmsContext;

    @Resource(mappedName = "java:jboss/exported/jms/queue/ExampleQueue")
    private Queue exampleQueue;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAllTeams")
    public List<Team> getAllTeams() {
        TypedQuery<Team> query = entityManager.createQuery("SELECT t FROM Team t", Team.class);
        return query.getResultList();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getTeamByName")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Team getTeam(@QueryParam("name") String name) {
        try {
            TypedQuery<Team> query = entityManager.createQuery("SELECT t FROM Team t WHERE t.name = :name", Team.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new TeamNotFoundException();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getTeamById/{id}")
    public Team getTeamById(@PathParam("id") Long id) {
        return entityManager.find(Team.class, id);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("save")
    public String save(Team team) {
        entityManager.persist(team);

        return "Saved!";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("saveMDB")
    public String saveMDB(Team team) {

        JMSProducer producer = jmsContext.createProducer();
        producer.send(exampleQueue, team);

        return "Saved!";
    }


    @GET
    @Path("test")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public String test() {

        a.fire();

        return "";
    }


}
