package gouri.ibk.interactivetrader.web;

import gouri.ibk.interactivetrader.bl.AccountFacade;
import gouri.ibk.interactivetrader.bl.AccountSummaryFacade;
import gouri.ibk.interactivetrader.model.Account;
import gouri.ibk.interactivetrader.model.WebOpsResult;
import gouri.ibk.interactivetrader.repo.AccountRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.*;

@RestController
@CrossOrigin({"http://localhost:3000", "http://localhost:5000"})
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Inject
    private AccountRepo repo;

    @Inject
    private AccountFacade accountFacade;

    @Inject
    private AccountSummaryFacade accountSummaryFacade;

    @GetMapping("/account/{id}")
    public Account findById(@NotNull @PathVariable("id") int id) {
        logger.info("account controller called with {}", id);
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/account/lname/{name}")
    public WebOpsResult<List<Account>> findByLastName(@NotNull @PathVariable("name") String name) {
        logger.info("/account/lname/ controller called with {}", name);
        return accountFacade.getAccountByLastName(name);
    }

    @GetMapping("/acctsummary/{id}")
    public WebOpsResult<AccountSummaryFacade.AccountSummary> getSummary(@NotNull @PathVariable("id") int id) {
        logger.info("getSummary controller called for account. {}", id);
        String ccy = "USD"; //TODO: Currency logic to be implemented by exchange code.
        Date cobDate = new Date(); //TODO: pass from UI for future enhancement
        return accountSummaryFacade.generateSummary(id, ccy, cobDate);
    }

}
