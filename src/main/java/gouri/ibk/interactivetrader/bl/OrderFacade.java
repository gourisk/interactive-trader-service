package gouri.ibk.interactivetrader.bl;

import gouri.ibk.interactivetrader.model.Account;
import gouri.ibk.interactivetrader.model.InstrumentMaster;
import gouri.ibk.interactivetrader.model.OrderMaster;
import gouri.ibk.interactivetrader.repo.AccountRepo;
import gouri.ibk.interactivetrader.repo.InstrumentRepo;
import gouri.ibk.interactivetrader.repo.OrderMasterRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Map;

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

    public OrderMaster createOrder(OrderMaster transientOrder) {
        OrderMaster savedOrder = transientOrder;
        String ticker = transientOrder.getInstrument().getTicker();
        Integer accountId = transientOrder.getAccountByTraderId().getAccountId();
        logger.info("get entities for for: ticker = {}, accountId={}", ticker, accountId);
        InstrumentMaster inst = instrumentRepo.findById(ticker).orElse(null);
        Account acct = accountRepo.findById(accountId).orElse(null);
        if(inst != null && acct != null) {
            transientOrder.setAccountByTraderId(acct)
                .setInstrument(inst)
                .setMarketValue(transientOrder.getPrice().multiply(new BigDecimal(transientOrder.getQuantity())));
            savedOrder = orderRepo.save(transientOrder);
        }
        return savedOrder;
    }

}
