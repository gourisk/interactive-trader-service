package gouri.ibk.interactivetrader.bl;

import gouri.ibk.interactivetrader.model.*;
import gouri.ibk.interactivetrader.repo.AccountRepo;
import gouri.ibk.interactivetrader.repo.InstrumentRepo;
import gouri.ibk.interactivetrader.repo.OrderMasterRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static gouri.ibk.interactivetrader.model.WebOpsResult.failureOf;
import static gouri.ibk.interactivetrader.model.WebOpsResult.successOf;

@Named
@Scope("prototype")
public class OrderFacade {

    private Logger logger = LoggerFactory.getLogger(OrderFacade.class);

    @Inject
    private OrderMasterRepo orderRepo;

    @Inject
    private InstrumentRepo instrumentRepo;

    @Inject
    private AccountRepo accountRepo;

    @Inject
    private AccountFacade accountFacade;

    @Inject
    private InstrumentFacade instrumentFacade;

    public boolean isValidOrder(Optional<OrderMaster> order) {
        return order
            .map(orderMaster -> orderMaster.getAccountByTraderId().getActive())
            .orElse(false);
    }

    /**
     * Not used anymore.
     *
     * @param transientOrder
     * @return
     * @see #createNewOrder instead
     */
    @Deprecated
    public OrderMaster createOrder(OrderMaster transientOrder) {
        OrderMaster savedOrder = transientOrder;
        String ticker = transientOrder.getInstrument().getTicker();
        Integer accountId = transientOrder.getAccountByTraderId().getAccountId();
        logger.info("get entities for for: ticker = {}, accountId={}", ticker, accountId);
        InstrumentMaster inst = instrumentRepo.findById(ticker).orElse(null);
        Account acct = accountRepo.findById(accountId).orElse(null);
        if (inst != null && acct != null) {
            transientOrder.setAccountByTraderId(acct)
                .setInstrument(inst)
                .setMarketValue(transientOrder.getPrice().multiply(new BigDecimal(transientOrder.getQuantity())));
            savedOrder = orderRepo.save(transientOrder);
        }
        return savedOrder;
    }

    /**
     * Validator method for Order Attributes
     *
     * @param order input {@link OrderMaster} object
     * @return Errors as a Map, empty otherwise
     */
    public Map<String, String> validateOrder(OrderMaster order) {
        logger.info("validation requested for Order: {}", order);
        Map<String, String> errors = new HashMap<>();
        //validate ticker
        String ticker = order.getInstrument().getTicker();
        Optional<InstrumentMaster> inst = instrumentFacade
            .getValidTicker(ticker)
            .map(instrumentMaster -> {
                order.setInstrument(instrumentMaster);
                return instrumentMaster;
            });
        if (inst.isEmpty()) {
            errors.put("instrument.ticker", "Invalid Ticker: " + ticker);
        }

        //validate account
        Integer accountId = order.getAccountByTraderId().getAccountId();
        Optional<Account> account = accountFacade
            .getValidAccount(accountId)
            .map(acct -> {
                order.setAccountByTraderId(acct);
                return acct;
            });
        if (account.isEmpty()) {
            errors.put("account.accountId", "Invalid Account: " + accountId);
        } else if ("B".equalsIgnoreCase(order.getBuySellFlag())) {
            //validate balance
            final BigDecimal orderValue = order.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()));
            Optional<AccountBalance> balance = account
                .flatMap(acct -> accountFacade.getAccountBalance(acct, order.getCurrency()));
            if (balance.isEmpty()) {
                errors.put("account.invalidBalance",
                    String.format("Invalid Account Balance: Currency %s doesn't exist for %s", order.getCurrency(), accountId));
            } else {
                AccountBalance bal = balance.get();
                if (bal.getCashBalance().subtract(orderValue).compareTo(bal.getMinBalance()) < 0) {
                    errors.put("account.insufficientBalance",
                        String.format("Insufficient balance to execute order. curr: %f, reqd: %f", bal.getCashBalance(), orderValue));
                }
            }
        }
        errors.forEach((k, v) -> logger.info("validation error[{}] : {}", k, v));
        return errors;
    }

    /**
     * creates a new order and saves to database.
     *
     * @param transientOrder the order input
     * @return {@link WebOpsResult} wrapped with persisted Order or errors in case of failure.
     * @see #validateOrder(OrderMaster) for order validation feature.
     * @see {@link AccountFacade} for Account related update for new order creation
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public WebOpsResult<OrderMaster> createNewOrder(OrderMaster transientOrder) {
        String ticker = transientOrder.getInstrument().getTicker();
        Integer accountId = transientOrder.getAccountByTraderId().getAccountId();
        logger.info("get entities for for: ticker = {}, accountId={}", ticker, accountId);

        Map<String, String> errors = this.validateOrder(transientOrder);
        if (!errors.isEmpty())
            return failureOf(errors);
        else {
            logger.info("order validation successful, {}", transientOrder);
            try {
                BigDecimal marketValue = transientOrder.getPrice().multiply(new BigDecimal(transientOrder.getQuantity()));
                BigDecimal balDelta =
                    "B".equalsIgnoreCase(transientOrder.getBuySellFlag()) ? marketValue.multiply(BigDecimal.valueOf(-1)) : marketValue;
                OrderMaster savedOrder = orderRepo.save(transientOrder.setMarketValue(marketValue).setStatus("OPEN"));
                accountFacade.updateAccountBalance(savedOrder.getAccountByTraderId(), savedOrder.getCurrency(), balDelta);

                return WebOpsResult.successOf(savedOrder);
            } catch (Exception e) {
                return failureOf("runTimeError", e.getMessage());
            }
        }
    }

    public Map<String, String> validateOrderForCancel(Optional<OrderMaster> order) {
        logger.info("validation requested for Order: {}", order);
        Map<String, String> errors = new HashMap<>();
        if (order.isEmpty()) {
            errors.put("order.orderId", "invalid order id");
        } else {
            if (!"OPEN".equalsIgnoreCase(order.get().getStatus())) {
                errors.put("order.status", String.format("Order is already %s", order.get().getStatus()));
            }
        }

        errors.forEach((k, v) -> logger.info("validation error[{}] : {}", k, v));
        return errors;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public WebOpsResult<OrderMaster> cancelOrder(OrderMaster transientOrder) {
        Optional<OrderMaster> persistentOrder = orderRepo.findById(transientOrder.getOrderId());
        logger.info("order in DB = {}", persistentOrder);
        Map<String, String> errors = this.validateOrderForCancel(persistentOrder);
        if (!errors.isEmpty())
            return failureOf(errors);
        else {
            logger.info("order validation successful, {}", persistentOrder);
            try {
                Optional<OrderMaster> cancelledOrder = persistentOrder.map(order -> {
                    BigDecimal marketValue = order.getMarketValue();
                    BigDecimal balDelta =
                        "S".equalsIgnoreCase(order.getBuySellFlag()) ? marketValue.multiply(BigDecimal.valueOf(-1)) : marketValue;
                    order.setStatus("CANCELLED");
                    OrderMaster savedOrder = orderRepo.save(order);
                    accountFacade.updateAccountBalance(savedOrder.getAccountByTraderId(), savedOrder.getCurrency(), balDelta);
                    return savedOrder;
                });
                return WebOpsResult.successOf(cancelledOrder);
            } catch (Exception e) {
                e.printStackTrace();
                return failureOf("runTimeError", e.getMessage());
            }
        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public WebOpsResult<OrderMaster> undoCancellation(OrderMaster transientOrder) {
        Optional<OrderMaster> persistentOrder = orderRepo.findById(transientOrder.getOrderId());
        logger.info("order in DB = {}", persistentOrder);
        return persistentOrder
            .filter(order -> "CANCELLED".equalsIgnoreCase(order.getStatus()))
            .map(order -> orderRepo.save(order.setStatus("OPEN")))
            .map(WebOpsResult::successOf)
            .orElse(failureOf("order.invalidId", "Invalid Order Id " + transientOrder.getOrderId()));
    }

    public WebOpsResult<Integer> getTodaysOrderCountForAccount(int accountId) {
        return WebOpsResult.successOf(orderRepo.getTradeCountForToday(accountId));
    }

}
