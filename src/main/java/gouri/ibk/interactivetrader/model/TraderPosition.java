package gouri.ibk.interactivetrader.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class TraderPosition {

    @EmbeddedId
    private TraderPositionKey id;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal marketValue;
    private BigDecimal openMarketValue;
    private BigDecimal cashBalance;
    private BigDecimal openCashBalance;
    private BigDecimal totalPl;

    public TraderPositionKey getId() {
        return id;
    }

    public TraderPosition setId(TraderPositionKey id) {
        this.id = id;
        return this;
    }

    @Basic
    @Column(name = "Quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public TraderPosition setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Basic
    @Column(name = "Price")
    public BigDecimal getPrice() {
        return price;
    }

    public TraderPosition setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Basic
    @Column(name = "MARKET_VALUE")
    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public TraderPosition setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
        return this;
    }

    @Basic
    @Column(name = "OPEN_MARKET_VALUE")
    public BigDecimal getOpenMarketValue() {
        return openMarketValue;
    }

    public TraderPosition setOpenMarketValue(BigDecimal openMarketValue) {
        this.openMarketValue = openMarketValue;
        return this;
    }

    @Basic
    @Column(name = "CASH_BALANCE")
    public BigDecimal getCashBalance() {
        return cashBalance;
    }

    public TraderPosition setCashBalance(BigDecimal cashBalance) {
        this.cashBalance = cashBalance;
        return this;
    }

    @Basic
    @Column(name = "OPEN_CASH_BALANCE")
    public BigDecimal getOpenCashBalance() {
        return openCashBalance;
    }

    public TraderPosition setOpenCashBalance(BigDecimal openCashBalance) {
        this.openCashBalance = openCashBalance;
        return this;
    }

    @Basic
    @Column(name = "TOTAL_PL")
    public BigDecimal getTotalPl() {
        return totalPl;
    }

    public TraderPosition setTotalPl(BigDecimal totalPl) {
        this.totalPl = totalPl;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TraderPosition that = (TraderPosition) o;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (marketValue != null ? marketValue.hashCode() : 0);
        result = 31 * result + (openMarketValue != null ? openMarketValue.hashCode() : 0);
        result = 31 * result + (cashBalance != null ? cashBalance.hashCode() : 0);
        result = 31 * result + (openCashBalance != null ? openCashBalance.hashCode() : 0);
        result = 31 * result + (totalPl != null ? totalPl.hashCode() : 0);
        return result;
    }
}
