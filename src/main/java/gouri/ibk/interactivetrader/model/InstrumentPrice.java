package gouri.ibk.interactivetrader.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class InstrumentPrice {
    private Integer priceKey;
    private Date reportDate;
    private String ticker;
    private BigDecimal bidPrice;
    private BigDecimal askPrice;
    private BigDecimal midPrice;
    private String curveName;

    @Id
    @Column(name = "PriceKey")
    public Integer getPriceKey() {
        return priceKey;
    }

    public InstrumentPrice setPriceKey(Integer priceKey) {
        this.priceKey = priceKey;
        return this;
    }

    @Basic
    @Column(name = "ReportDate")
    public Date getReportDate() {
        return reportDate;
    }

    public InstrumentPrice setReportDate(Date reportDate) {
        this.reportDate = reportDate;
        return this;
    }

    @Column(name = "Ticker")
    public String getTicker() {
        return ticker;
    }

    public InstrumentPrice setTicker(String ticker) {
        this.ticker = ticker;
        return this;
    }

    @Basic
    @Column(name = "BidPrice")
    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public InstrumentPrice setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
        return this;
    }

    @Basic
    @Column(name = "AskPrice")
    public BigDecimal getAskPrice() {
        return askPrice;
    }

    public InstrumentPrice setAskPrice(BigDecimal askPrice) {
        this.askPrice = askPrice;
        return this;
    }

    @Basic
    @Column(name = "MidPrice")
    public BigDecimal getMidPrice() {
        return midPrice;
    }

    public InstrumentPrice setMidPrice(BigDecimal midPrice) {
        this.midPrice = midPrice;
        return this;
    }

    @Basic
    @Column(name = "CurveName")
    public String getCurveName() {
        return curveName;
    }

    public InstrumentPrice setCurveName(String curveName) {
        this.curveName = curveName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstrumentPrice that = (InstrumentPrice) o;

        if (priceKey != null ? !priceKey.equals(that.priceKey) : that.priceKey != null) return false;
        if (reportDate != null ? !reportDate.equals(that.reportDate) : that.reportDate != null) return false;
        if (bidPrice != null ? !bidPrice.equals(that.bidPrice) : that.bidPrice != null) return false;
        if (askPrice != null ? !askPrice.equals(that.askPrice) : that.askPrice != null) return false;
        if (midPrice != null ? !midPrice.equals(that.midPrice) : that.midPrice != null) return false;
        if (curveName != null ? !curveName.equals(that.curveName) : that.curveName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = priceKey != null ? priceKey.hashCode() : 0;
        result = 31 * result + (reportDate != null ? reportDate.hashCode() : 0);
        result = 31 * result + (bidPrice != null ? bidPrice.hashCode() : 0);
        result = 31 * result + (askPrice != null ? askPrice.hashCode() : 0);
        result = 31 * result + (midPrice != null ? midPrice.hashCode() : 0);
        result = 31 * result + (curveName != null ? curveName.hashCode() : 0);
        return result;
    }
}
