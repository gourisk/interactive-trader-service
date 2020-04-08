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
        //you can use  your data here
    }

}
