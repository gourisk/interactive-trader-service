package gouri.ibk.interactivetrader.web;

import gouri.ibk.interactivetrader.bl.OrderFacade;
import gouri.ibk.interactivetrader.model.OrderMaster;
import gouri.ibk.interactivetrader.repo.OrderMasterRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin({"http://localhost:3000", "http://localhost:5000"})
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Inject
    private OrderMasterRepo orderRepo;

    @Inject
    OrderFacade orderFacade;

    @Inject
    SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/orders/{id}")
    public List<Map<String, Object>> getOrdersByAccount(@NotNull @PathVariable("id") int accountId) {
        logger.info("Find orders for account: {}", accountId);
        List<OrderMaster> orders = orderRepo.findTop5ByAccountByTraderId_AccountId(accountId,
            Sort.by(Sort.Direction.DESC, "orderId"));

        List<Map<String, Object>> returnOrders = new ArrayList<>();

        for(OrderMaster order: orders) {
            Map<String, Object> orderMap = formatOrder(order);
            returnOrders.add(orderMap);
        }
        return returnOrders;
    }

    private Map<String, Object> formatOrder(OrderMaster order) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("orderId", order.getOrderId());
        orderMap.put("ticker", order.getInstrument().getTicker());
        orderMap.put("quantity", order.getQuantity());
        orderMap.put("marketValue", order.getMarketValue());
        orderMap.put("price", order.getPrice());
        orderMap.put("tradeDate", formatter.format(order.getTradeDate()));
        orderMap.put("executedTime", formatter.format(order.getExecutedTime()));

        return orderMap;
    }

    @GetMapping("/orders/count/{id}")
    public Map<String, Integer> getOrderCount(@NotNull @PathVariable("id") int accountId) {
        logger.info("find today's trade count for: {}", accountId);
        Map<String, Integer> model = new HashMap<>();
        model.put("result", orderRepo.countByTradeDateAndAccountByTraderId_AccountId(
            new Date(new java.util.Date().getTime()), accountId));
        return model;
    }

    @PostMapping(value = "/orders/create",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public OrderMaster createOrder(@RequestBody OrderMaster inputOrder) {
        logger.info("input order received as : {}", inputOrder);
        OrderMaster savedOrder = orderFacade.createOrder(inputOrder);
        simpMessagingTemplate.convertAndSend("/topics/trades", formatOrder(savedOrder));
        return savedOrder;
    }

}
