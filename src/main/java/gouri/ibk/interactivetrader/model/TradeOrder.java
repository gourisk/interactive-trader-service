package gouri.ibk.interactivetrader.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class TradeOrder {

    private Integer tradeId;
    private String tradeDesc;
    private String bookName;
    private Timestamp tradeDate;
    private String buySellFlag;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal marketValue;
    private String tradeSourceId;
    private Instrument instrumentMasterByTicker;
    private Account accountByTraderId;

    @Id
    @Column(name = "Tradeid")
    public Integer getTradeId() {
        return tradeId;
    }

    public TradeOrder setTradeId(Integer tradeid) {
        this.tradeId = tradeid;
        return this;
    }

    @Basic
    @Column(name = "TradeDesc")
    public String getTradeDesc() {
        return tradeDesc;
    }

    public TradeOrder setTradeDesc(String tradeDesc) {
        this.tradeDesc = tradeDesc;
        return this;
    }

    @Basic
    @Column(name = "BookName")
    public String getBookName() {
        return bookName;
    }

    public TradeOrder setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    @Basic
    @Column(name = "TradeDate")
    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public TradeOrder setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
        return this;
    }

    @Basic
    @Column(name = "BuySellFlag")
    public String getBuySellFlag() {
        return buySellFlag;
    }

    public TradeOrder setBuySellFlag(String buySellFlag) {
        this.buySellFlag = buySellFlag;
        return this;
    }

    @Basic
    @Column(name = "Quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public TradeOrder setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Basic
    @Column(name = "Price")
    public BigDecimal getPrice() {
        return price;
    }

    public TradeOrder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Basic
    @Column(name = "MarketValue")
    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public TradeOrder setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
        return this;
    }

    @Basic
    @Column(name = "TradeSourceId")
    public String getTradeSourceId() {
        return tradeSourceId;
    }

    public TradeOrder setTradeSourceId(String tradeSourceId) {
        this.tradeSourceId = tradeSourceId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeOrder that = (TradeOrder) o;

        if (tradeId != null ? !tradeId.equals(that.tradeId) : that.tradeId != null) return false;
        if (tradeDesc != null ? !tradeDesc.equals(that.tradeDesc) : that.tradeDesc != null) return false;
        if (bookName != null ? !bookName.equals(that.bookName) : that.bookName != null) return false;
        if (tradeDate != null ? !tradeDate.equals(that.tradeDate) : that.tradeDate != null) return false;
        if (buySellFlag != null ? !buySellFlag.equals(that.buySellFlag) : that.buySellFlag != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (marketValue != null ? !marketValue.equals(that.marketValue) : that.marketValue != null) return false;
        if (tradeSourceId != null ? !tradeSourceId.equals(that.tradeSourceId) : that.tradeSourceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tradeId != null ? tradeId.hashCode() : 0;
        result = 31 * result + (tradeDesc != null ? tradeDesc.hashCode() : 0);
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (tradeDate != null ? tradeDate.hashCode() : 0);
        result = 31 * result + (buySellFlag != null ? buySellFlag.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (marketValue != null ? marketValue.hashCode() : 0);
        result = 31 * result + (tradeSourceId != null ? tradeSourceId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Ticker", referencedColumnName = "Ticker", nullable = false)
    public Instrument getInstrumentMasterByTicker() {
        return instrumentMasterByTicker;
    }

    public TradeOrder setInstrumentMasterByTicker(Instrument instrumentMasterByTicker) {
        this.instrumentMasterByTicker = instrumentMasterByTicker;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "TraderId", referencedColumnName = "AccountId", nullable = false)
    public Account getAccountByTraderId() {
        return accountByTraderId;
    }

    public TradeOrder setAccountByTraderId(Account accountByTraderId) {
        this.accountByTraderId = accountByTraderId;
        return this;
    }

}
