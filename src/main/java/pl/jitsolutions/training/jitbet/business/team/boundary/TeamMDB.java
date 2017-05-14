package pl.jitsolutions.training.jitbet.business.team.boundary;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jitsolutions.training.jitbet.business.team.entity.Team;

@MessageDriven(
        mappedName = "ExampleQueue",
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/ExampleQueue"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        }
)
public class TeamMDB implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(TeamMDB.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void onMessage(Message message) {
        try {
            LOG.info("Saving new team...");

            entityManager.persist(message.getBody(Team.class));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
