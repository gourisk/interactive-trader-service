package gouri.ibk.interactivetrader.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


public class InstrumentPriceHistory {
    private Date reportDate;
    private BigDecimal bidPrice;
    private BigDecimal askPrice;
    private BigDecimal midPrice;
    private Timestamp lastUpdated;
    private String ticker;

    public Date getReportDate() {
        return reportDate;
    }

    public InstrumentPriceHistory setReportDate(Date reportDate) {
        this.reportDate = reportDate;
        return this;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public InstrumentPriceHistory setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
        return this;
    }

    public BigDecimal getAskPrice() {
        return askPrice;
    }

    public InstrumentPriceHistory setAskPrice(BigDecimal askPrice) {
        this.askPrice = askPrice;
        return this;
    }

    public BigDecimal getMidPrice() {
        return midPrice;
    }

    public InstrumentPriceHistory setMidPrice(BigDecimal midPrice) {
        this.midPrice = midPrice;
        return this;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public InstrumentPriceHistory setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    public String getTicker() {
        return ticker;
    }

    public InstrumentPriceHistory setTicker(String ticker) {
        this.ticker = ticker;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstrumentPriceHistory that = (InstrumentPriceHistory) o;

        if (reportDate != null ? !reportDate.equals(that.reportDate) : that.reportDate != null) return false;
        if (bidPrice != null ? !bidPrice.equals(that.bidPrice) : that.bidPrice != null) return false;
        if (askPrice != null ? !askPrice.equals(that.askPrice) : that.askPrice != null) return false;
        if (midPrice != null ? !midPrice.equals(that.midPrice) : that.midPrice != null) return false;
        if (lastUpdated != null ? !lastUpdated.equals(that.lastUpdated) : that.lastUpdated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reportDate != null ? reportDate.hashCode() : 0;
        result = 31 * result + (bidPrice != null ? bidPrice.hashCode() : 0);
        result = 31 * result + (askPrice != null ? askPrice.hashCode() : 0);
        result = 31 * result + (midPrice != null ? midPrice.hashCode() : 0);
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0);
        return result;
    }

}
