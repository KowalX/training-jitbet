package pl.jitsolutions.training.jitbet.business.team.control;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
public class A {

    @EJB
    private B b;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void fire() {

        b.fire();
    }
}
