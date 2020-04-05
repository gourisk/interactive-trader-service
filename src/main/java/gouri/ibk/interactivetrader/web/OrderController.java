package gouri.ibk.interactivetrader.web;

import gouri.ibk.interactivetrader.model.OrderMaster;
import gouri.ibk.interactivetrader.repo.OrderMasterRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin({"http://localhost:3000", "http://localhost:5000"})
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Inject
    private OrderMasterRepo orderRepo;

    @GetMapping("/orders/{id}")
    public List<OrderMaster> getOrdersByAccount(@NotNull @PathVariable("id") int accountId) {
        logger.info("Find orders for account: {}", accountId);
        return orderRepo.findByAccountByTraderId_AccountId(accountId);
    }

}
