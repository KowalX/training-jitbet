package pl.jitsolutions.training.jitbet.business.team.control;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
public class B {

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void fire() {

        try {
            Thread.sleep(10001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
