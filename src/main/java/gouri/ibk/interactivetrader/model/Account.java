package gouri.ibk.interactivetrader.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Account {

    private Integer accountId;
    private String firstName;
    private String lastName;
    private String email;
    private String accountType;
    private Timestamp createDate;
    private Timestamp modifiedDate;
    private Boolean isActive;

    @Id
    @Column(name = "ACCOUNTID")
    public Integer getAccountId() {
        return accountId;
    }

    public Account setAccountId(Integer accountId) {
        this.accountId = accountId;
        return this;
    }

    @Basic
    @Column(name = "FIRSTNAME")
    public String getFirstName() {
        return firstName;
    }

    public Account setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Basic
    @Column(name = "LASTNAME")
    public String getLastName() {
        return lastName;
    }

    public Account setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public Account setEmail(String email) {
        this.email = email;
        return this;
    }

    @Basic
    @Column(name = "ACCOUNTTYPE")
    public String getAccountType() {
        return accountType;
    }

    public Account setAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    @Basic
    @Column(name = "CREATEDATE")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public Account setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
        return this;
    }

    @Basic
    @Column(name = "MODIFIEDDATE")
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public Account setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    @Basic
    @Column(name = "ISACTIVE")
    public Boolean getActive() {
        return isActive;
    }

    public Account setActive(Boolean active) {
        isActive = active;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (accountId != null ? !accountId.equals(account.accountId) : account.accountId != null) return false;
        if (firstName != null ? !firstName.equals(account.firstName) : account.firstName != null) return false;
        if (lastName != null ? !lastName.equals(account.lastName) : account.lastName != null) return false;
        if (email != null ? !email.equals(account.email) : account.email != null) return false;
        if (accountType != null ? !accountType.equals(account.accountType) : account.accountType != null) return false;
        if (createDate != null ? !createDate.equals(account.createDate) : account.createDate != null) return false;
        if (modifiedDate != null ? !modifiedDate.equals(account.modifiedDate) : account.modifiedDate != null)
            return false;
        if (isActive != null ? !isActive.equals(account.isActive) : account.isActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountId != null ? accountId.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (accountType != null ? accountType.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        return result;
    }
}
