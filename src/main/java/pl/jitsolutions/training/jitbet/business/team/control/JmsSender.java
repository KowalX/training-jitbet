package pl.jitsolutions.training.jitbet.business.team.control;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

import pl.jitsolutions.training.jitbet.business.team.entity.Team;

@Stateless
public class JmsSender {

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext jmsContext;

    @Resource(mappedName = "java:jboss/exported/jms/queue/ExampleQueue")
    private Queue exampleQueue;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void send(Team team) {
        JMSProducer jmsProducer = jmsContext.createProducer();

        jmsProducer.send(exampleQueue, team);
    }
}
