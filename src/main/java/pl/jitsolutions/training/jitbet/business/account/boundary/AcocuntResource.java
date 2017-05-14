package pl.jitsolutions.training.jitbet.business.account.boundary;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.jitsolutions.training.jitbet.business.account.entity.Account;

@Path("account")
public class AcocuntResource {

    @Inject
    @LoggedAccount
    private Account account;

    @GET
    @Path("currentLogin")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCurrentLogin() {
        CDI<Object> cdi = CDI.current();

        AnnotationLiteral<LoggedAccount> loggedAccountLiteral = new AnnotationLiteral<LoggedAccount>() {
        };

        Instance<Account> accountInstance = cdi.select(Account.class, loggedAccountLiteral);

        return accountInstance.get().getLogin();
    }
}
