package gouri.ibk.interactivetrader.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class OrderMaster {
    private Integer orderId;
    private String orderDesc;
    private Timestamp tradeDate;
    private String buySellFlag;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal marketValue;
    private String tradeSource;
    private Timestamp executedTime;
    private InstrumentMaster instrument;
    private Account accountByTraderId;

    @Id
    @Column(name = "OrderId")
    public Integer getOrderId() {
        return orderId;
    }

    public OrderMaster setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    @Basic
    @Column(name = "OrderDesc")
    public String getOrderDesc() {
        return orderDesc;
    }

    public OrderMaster setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
        return this;
    }

    @Basic
    @Column(name = "TradeDate")
    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public OrderMaster setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
        return this;
    }

    @Basic
    @Column(name = "BuySellFlag")
    public String getBuySellFlag() {
        return buySellFlag;
    }

    public OrderMaster setBuySellFlag(String buySellFlag) {
        this.buySellFlag = buySellFlag;
        return this;
    }

    @Basic
    @Column(name = "Quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public OrderMaster setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Basic
    @Column(name = "Price")
    public BigDecimal getPrice() {
        return price;
    }

    public OrderMaster setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Basic
    @Column(name = "MarketValue")
    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public OrderMaster setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
        return this;
    }

    @Basic
    @Column(name = "TradeSource")
    public String getTradeSource() {
        return tradeSource;
    }

    public OrderMaster setTradeSource(String tradeSource) {
        this.tradeSource = tradeSource;
        return this;
    }

    @Basic
    @Column(name = "ExecutedTime")
    public Timestamp getExecutedTime() {
        return executedTime;
    }

    public OrderMaster setExecutedTime(Timestamp executedTime) {
        this.executedTime = executedTime;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderMaster that = (OrderMaster) o;

        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (orderDesc != null ? !orderDesc.equals(that.orderDesc) : that.orderDesc != null) return false;
        if (tradeDate != null ? !tradeDate.equals(that.tradeDate) : that.tradeDate != null) return false;
        if (buySellFlag != null ? !buySellFlag.equals(that.buySellFlag) : that.buySellFlag != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (marketValue != null ? !marketValue.equals(that.marketValue) : that.marketValue != null) return false;
        if (tradeSource != null ? !tradeSource.equals(that.tradeSource) : that.tradeSource != null)
            return false;
        if (executedTime != null ? !executedTime.equals(that.executedTime) : that.executedTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (orderDesc != null ? orderDesc.hashCode() : 0);
        result = 31 * result + (tradeDate != null ? tradeDate.hashCode() : 0);
        result = 31 * result + (buySellFlag != null ? buySellFlag.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (marketValue != null ? marketValue.hashCode() : 0);
        result = 31 * result + (tradeSource != null ? tradeSource.hashCode() : 0);
        result = 31 * result + (executedTime != null ? executedTime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Ticker", referencedColumnName = "Ticker", nullable = false)
    public InstrumentMaster getInstrument() {
        return instrument;
    }

    public OrderMaster setInstrument(InstrumentMaster instrumentMasterByTicker) {
        this.instrument = instrumentMasterByTicker;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "TraderId", referencedColumnName = "AccountId", nullable = false)
    public Account getAccountByTraderId() {
        return accountByTraderId;
    }

    public OrderMaster setAccountByTraderId(Account accountByTraderId) {
        this.accountByTraderId = accountByTraderId;
        return this;
    }
}
