package gouri.ibk.interactivetrader.web;

import gouri.ibk.interactivetrader.model.Account;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@CrossOrigin({"http://localhost:3000", "http://localhost:5000"})
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Inject
    private AccountRepo repo;

    @GetMapping("/account/{id}")
    public Account findById(@NotNull @PathVariable("id") int id) {
        logger.info("account controller called with {}", id);
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/account/lname/{name}")
    public List<Account> findByLastName(@NotNull @PathVariable("name") String name) {
        logger.info("/account/lname/ controller called with {}", name);
        return repo.findByLastName(name);
    }

    @GetMapping("/acctsummary")
    public Map<String, ? extends Object> getSummary() {
        Map<String, Integer> map = new HashMap<>();
        logger.info("getSummary controller called.");
        int number = new Random().nextInt(1000);
        map.put("result", number);
        return map;
    }

}
