package gouri.ibk.interactivetrader.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Instrument {
    private String ticker;
    private String issuer;
    private String parentIssuer;
    private String marketSector;
    private String currencyCode;

    @Id
    @Column(name = "Ticker")
    public String getTicker() {
        return ticker;
    }

    public Instrument setTicker(String ticker) {
        this.ticker = ticker;
        return this;
    }

    @Basic
    @Column(name = "Issuer")
    public String getIssuer() {
        return issuer;
    }

    public Instrument setIssuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    @Basic
    @Column(name = "ParentIssuer")
    public String getParentIssuer() {
        return parentIssuer;
    }

    public Instrument setParentIssuer(String parentIssuer) {
        this.parentIssuer = parentIssuer;
        return this;
    }

    @Basic
    @Column(name = "MarketSector")
    public String getMarketSector() {
        return marketSector;
    }

    public Instrument setMarketSector(String marketSector) {
        this.marketSector = marketSector;
        return this;
    }

    @Basic
    @Column(name = "CurrencyCode")
    public String getCurrencyCode() {
        return currencyCode;
    }

    public Instrument setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Instrument that = (Instrument) o;

        if (ticker != null ? !ticker.equals(that.ticker) : that.ticker != null) return false;
        if (issuer != null ? !issuer.equals(that.issuer) : that.issuer != null) return false;
        if (parentIssuer != null ? !parentIssuer.equals(that.parentIssuer) : that.parentIssuer != null) return false;
        if (marketSector != null ? !marketSector.equals(that.marketSector) : that.marketSector != null) return false;
        if (currencyCode != null ? !currencyCode.equals(that.currencyCode) : that.currencyCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ticker != null ? ticker.hashCode() : 0;
        result = 31 * result + (issuer != null ? issuer.hashCode() : 0);
        result = 31 * result + (parentIssuer != null ? parentIssuer.hashCode() : 0);
        result = 31 * result + (marketSector != null ? marketSector.hashCode() : 0);
        result = 31 * result + (currencyCode != null ? currencyCode.hashCode() : 0);
        return result;
    }
}
