package gouri.ibk.interactivetrader.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class AccountBalance {
    private Integer id;
    private String currencyCode;
    private BigDecimal cashBalance;
    private BigDecimal minBalance;

    private Account account = new Account();

    @Id
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public AccountBalance setId(Integer id) {
        this.id = id;
        return this;
    }

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

    @Basic
    @Column(name = "MinBalance")
    public BigDecimal getMinBalance() {
        return minBalance;
    }

    public AccountBalance setMinBalance(BigDecimal minBalance) {
        this.minBalance = minBalance;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "AccountId", nullable = false)
    @JsonIgnore
    public Account getAccount() {
        return account;
    }

    public AccountBalance setAccount(Account account) {
        this.account = account;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountBalance that = (AccountBalance) o;
        return currencyCode.equals(that.currencyCode) &&
            account.equals(that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyCode, account);
    }
}
