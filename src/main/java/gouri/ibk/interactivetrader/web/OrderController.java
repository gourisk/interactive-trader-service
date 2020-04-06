package gouri.ibk.interactivetrader.web;

import gouri.ibk.interactivetrader.model.OrderMaster;
import gouri.ibk.interactivetrader.repo.OrderMasterRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin({"http://localhost:3000", "http://localhost:5000"})
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Inject
    private OrderMasterRepo orderRepo;

    @GetMapping("/orders/{id}")
    public List<Map<String, Object>> getOrdersByAccount(@NotNull @PathVariable("id") int accountId) {
        logger.info("Find orders for account: {}", accountId);
        List<OrderMaster> orders = orderRepo.findTop5ByAccountByTraderId_AccountId(accountId,
            Sort.by(Sort.Direction.DESC, "tradeDate"));

        List<Map<String, Object>> returnOrders = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        for(OrderMaster order: orders) {
            Map<String, Object> orderMap = new HashMap<>();
            orderMap.put("orderId", order.getOrderId());
            orderMap.put("ticker", order.getInstrument().getTicker());
            orderMap.put("quantity", order.getQuantity());
            orderMap.put("marketValue", order.getMarketValue());
            orderMap.put("price", order.getPrice());
            orderMap.put("tradeDate", formatter.format(order.getTradeDate()));
            orderMap.put("executedTime", formatter.format(order.getExecutedTime()));
            returnOrders.add(orderMap);
        }
        return returnOrders;
    }

}
