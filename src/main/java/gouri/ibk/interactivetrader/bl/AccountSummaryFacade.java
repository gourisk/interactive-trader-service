package gouri.ibk.interactivetrader.bl;

import gouri.ibk.interactivetrader.model.*;
import gouri.ibk.interactivetrader.repo.OrderMasterRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static gouri.ibk.interactivetrader.model.WebOpsResult.failureOf;
import static gouri.ibk.interactivetrader.model.WebOpsResult.successOf;

@Named
public class AccountSummaryFacade {

    Logger logger = LoggerFactory.getLogger(AccountSummaryFacade.class);

    @Inject
    AccountFacade accountFacade;

    @Inject
    OrderMasterRepo orderRepo;

    @Inject
    InstrumentFacade instFacade;

    /**
     * Data Transfer Object as a simplified container for AccountSummary
     * AccountSummaryFacade owns the responsibility for summarizing the account details.
     * This the DTO lacks usability outside and thus is defined as an inner class
     */
    public static class AccountSummary {

        Integer accountId;
        String name;
        String currency;
        Date tradeDate;
        BigDecimal accountBalance;
        Integer orderCount;
        BigDecimal dailyPL = BigDecimal.ZERO;
        BigDecimal mtdPL = BigDecimal.ZERO; //TODO: to be implemented

        public AccountSummary(Account account, String ccy, Date cob) {
            this.accountId = account.getAccountId();
            this.name = account.getFirstName() + " " + account.getLastName();
            this.currency = ccy;
            this.tradeDate = cob;
        }

        public Integer getAccountId() {
            return accountId;
        }

        public AccountSummary setAccountId(Integer accountId) {
            this.accountId = accountId;
            return this;
        }

        public String getName() {
            return name;
        }

        public AccountSummary setName(String name) {
            this.name = name;
            return this;
        }

        public String getCurrency() {
            return currency;
        }

        public AccountSummary setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Date getTradeDate() {
            return tradeDate;
        }

        public AccountSummary setTradeDate(Date tradeDate) {
            this.tradeDate = tradeDate;
            return this;
        }

        public BigDecimal getAccountBalance() {
            return accountBalance;
        }

        public AccountSummary setAccountBalance(BigDecimal accountBalance) {
            this.accountBalance = accountBalance;
            return this;
        }

        public Integer getOrderCount() {
            return orderCount;
        }

        public AccountSummary setOrderCount(Integer orderCount) {
            this.orderCount = orderCount;
            return this;
        }

        public BigDecimal getDailyPL() {
            return dailyPL;
        }

        public AccountSummary setDailyPL(BigDecimal dailyPL) {
            this.dailyPL = dailyPL;
            return this;
        }

        public BigDecimal getMtdPL() {
            return mtdPL;
        }

        public AccountSummary setMtdPL(BigDecimal mtdPL) {
            this.mtdPL = mtdPL;
            return this;
        }
    }

    /**
     * generates a summary of the components requested in Ui Dashboard summary
     * Account Balance  current balance on the the account
     * Trade count: no of orders today
     * Daily PL: Total PL for orders executed today.
     * Month to date PL : To be implemented.
     *
     * @param accountId {@link Account} holder
     * @param currency  exchange currency: defaults to USD
     * @param date      cob date
     * @return #AccountSummary objects with simplified summary
     */
    public WebOpsResult<AccountSummary> generateSummary(int accountId, String currency, Date date) {
        Optional<Account> account = accountFacade.getValidAccount(accountId);
        return account
            .map(acct -> {
                List<OrderMaster> orders =
                    orderRepo.findOrdersByTraderAndDate(accountId, new java.sql.Date(date.getTime()));
                AccountSummary summary = new AccountSummary(acct, currency, date)
                    .setAccountBalance(accountFacade.getCurrentBalance(acct, currency))
                    .setOrderCount(orders.size())
                    .setDailyPL(this.getDailyPLForAccount(acct, date, orders));
                return successOf(summary);
            })
            .orElse(failureOf("account.invalidAccount", "invalid accountId " + accountId));
    }

    /**
     * PL calc from order
     */
    private final BiFunction<OrderMaster, InstrumentPrice, BigDecimal> calcPlForOrder = (order, inst) ->
        "CANCELLED".equalsIgnoreCase(order.getStatus()) ? BigDecimal.ZERO :
            BigDecimal.valueOf(order.getQuantity())
                .multiply(inst.getMidPrice().subtract(order.getPrice()));


    /**
     * calculate PL for a date.
     * Generates the PL on the fly from the Orders executed on the date.
     * compares the order price against latest price for the ticker and calculates P/L.
     *
     * @param account {@link Account} for the trader
     * @param date    cobdate
     * @param orders  orders for the day
     * @return Total PL for the day
     * @see #calcPlForOrder
     */
    private BigDecimal getDailyPLForAccount(Account account, Date date, List<OrderMaster> orders) {

        Set<String> tickers = orders.stream()
            .map(OrderMaster::getInstrument)
            .map(InstrumentMaster::getTicker)
            .collect(Collectors.toSet());
        logger.info("going to fetch current price details for tickers: {}", tickers);
        Map<String, InstrumentPrice> latestPrices = instFacade.getCurrentPriceMulti(tickers.toArray(new String[0]))
            .stream()
            .collect(Collectors.toMap(InstrumentPrice::getTicker, Function.identity()));
        return orders.stream()
            .map(order -> calcPlForOrder.apply(order, latestPrices.get(order.getInstrument().getTicker())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
