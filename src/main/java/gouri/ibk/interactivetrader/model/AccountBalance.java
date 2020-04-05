package gouri.ibk.interactivetrader.model;

import javax.persistence.*;
import java.math.BigDecimal;

//@Entity
public class AccountBalance {
    private String currencyCode;
    private BigDecimal cashBalance;
    private Integer accountId;

    @Basic
    @Column(name = "CurrencyCode")
    public String getCurrencyCode() {
        return currencyCode;
    }

    public AccountBalance setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    @Basic
    @Column(name = "CashBalance")
    public BigDecimal getCashBalance() {
        return cashBalance;
    }

    public AccountBalance setCashBalance(BigDecimal cashBalance) {
        this.cashBalance = cashBalance;
        return this;
    }

    @Column(name = "AccountId")
    public Integer getAccountId() {
        return accountId;
    }

    public AccountBalance setAccountId(Integer accountId) {
        this.accountId = accountId;
        return this;
    }
}
