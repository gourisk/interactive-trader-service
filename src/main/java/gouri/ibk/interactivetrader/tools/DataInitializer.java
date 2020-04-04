package gouri.ibk.interactivetrader.tools;

import gouri.ibk.interactivetrader.model.Account;
import gouri.ibk.interactivetrader.repo.AccountRepo;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.sql.Timestamp;

@Named
@Singleton
public class DataInitializer {

    @Inject
    AccountRepo repo;

    public void initData() {
        Account account = new Account()
            .setAccountId(1)
            .setFirstName("Gouri")
            .setLastName("Khatua")
            .setEmail("gouri.khatua@gmail.com")
            .setAccountType("TRADER")
            .setActive(true)
            .setCreateDate(new Timestamp(System.currentTimeMillis()))
            .setModifiedDate(new Timestamp(System.currentTimeMillis()));
        repo.save(account);
    }

}
