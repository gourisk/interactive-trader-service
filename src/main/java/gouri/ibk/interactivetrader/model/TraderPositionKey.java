package gouri.ibk.interactivetrader.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Date;

@Embeddable
public class TraderPositionKey implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Column(name = "REPORT_DATE")
    private Date reportDate;
    @Column(name="ACCOUNT_ID")
    private Integer accountId;
    @Column(name = "TICKER")
    private String ticker;

    public TraderPositionKey(){}

    public Date getReportDate() {
        return reportDate;
    }

    public TraderPositionKey setReportDate(Date reportDate) {
        this.reportDate = reportDate;
        return this;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public TraderPositionKey setAccountId(Integer accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getTicker() {
        return ticker;
    }

    public TraderPositionKey setTicker(String ticker) {
        this.ticker = ticker;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TraderPositionKey that = (TraderPositionKey) o;
        return this.reportDate != null && this.reportDate.equals(that.reportDate)
            && this.accountId != null && this.accountId.equals(that.accountId)
            && this.ticker != null && this.ticker.equals(that.ticker);
    }

    @Override
    public int hashCode() {
        int result = (reportDate != null ? reportDate.hashCode() : 0);
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (ticker != null ? ticker.hashCode() : 0);
        return result;
    }
}
