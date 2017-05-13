package pl.jitsolutions.training.jitbet.business.account.boundary;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

import pl.jitsolutions.training.jitbet.business.account.entity.Account;

public class AccountProducer {

    @Produces
    @LoggedAccount
    @SessionScoped
    public Account getLoggedAccount() {
        Account account = new Account();
        account.setLogin("admin");
        account.setPassword("1234");
        account.setEmail("admin@wp.pl");
        return account;
    }
}
