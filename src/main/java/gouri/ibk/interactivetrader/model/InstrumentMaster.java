package gouri.ibk.interactivetrader.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InstrumentMaster {
    private String ticker;
    private String issuer;
    private String exchangeCode;
    private String marketSector;
    private String currencyCode;

    @Id
    @Column(name = "TICKER")
    public String getTicker() {
        return ticker;
    }

    public InstrumentMaster setTicker(String ticker) {
        this.ticker = ticker;
        return this;
    }

    @Basic
    @Column(name = "ISSUER")
    public String getIssuer() {
        return issuer;
    }

    public InstrumentMaster setIssuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    @Basic
    @Column(name = "EXCHANGE_CODE")
    public String getExchangeCode() {
        return exchangeCode;
    }

    public InstrumentMaster setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
        return this;
    }

    @Basic
    @Column(name = "MARKET_SECTOR")
    public String getMarketSector() {
        return marketSector;
    }

    public InstrumentMaster setMarketSector(String marketSector) {
        this.marketSector = marketSector;
        return this;
    }

    @Basic
    @Column(name = "CURRENCY_CODE")
    public String getCurrencyCode() {
        return currencyCode;
    }

    public InstrumentMaster setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstrumentMaster that = (InstrumentMaster) o;

        if (ticker != null ? !ticker.equals(that.ticker) : that.ticker != null) return false;
        if (issuer != null ? !issuer.equals(that.issuer) : that.issuer != null) return false;
        if (exchangeCode != null ? !exchangeCode.equals(that.exchangeCode) : that.exchangeCode != null) return false;
        if (marketSector != null ? !marketSector.equals(that.marketSector) : that.marketSector != null) return false;
        if (currencyCode != null ? !currencyCode.equals(that.currencyCode) : that.currencyCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ticker != null ? ticker.hashCode() : 0;
        result = 31 * result + (issuer != null ? issuer.hashCode() : 0);
        result = 31 * result + (exchangeCode != null ? exchangeCode.hashCode() : 0);
        result = 31 * result + (marketSector != null ? marketSector.hashCode() : 0);
        result = 31 * result + (currencyCode != null ? currencyCode.hashCode() : 0);
        return result;
    }
}
