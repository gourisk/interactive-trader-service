package gouri.ibk.interactivetrader.bl;

import gouri.ibk.interactivetrader.model.Account;
import gouri.ibk.interactivetrader.model.AccountBalance;
import gouri.ibk.interactivetrader.model.WebOpsResult;
import gouri.ibk.interactivetrader.repo.AccountBalanceRepo;
import gouri.ibk.interactivetrader.repo.AccountRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Named
public class AccountFacade {

    private Logger logger = LoggerFactory.getLogger(AccountFacade.class);

    /**
     * Repository to manage CRUD operation on @{@link Account}
     */
    @Inject
    private AccountRepo accountRepo;

    /**
     * {@link AccountBalance} JPA repository
     */
    @Inject
    private AccountBalanceRepo balanceRepo;

    /**
     * provides the current balance of an account for a currency.
     *
     * @param account  {@link Account} object for which balance is desired
     * @param currency ISO code of the lookup currency e.g USD
     * @return Current Account Balance the currency specified
     */
    public Optional<AccountBalance> getAccountBalance(Account account, String currency) {
        String ccy = currency == null ? "USD" : currency;
        return account.getAccountBalances()
            .stream()
            .filter(accountBalance -> ccy.equalsIgnoreCase(accountBalance.getCurrencyCode()))
            .findFirst();
    }

    /**
     * provides the current cash balance of an account for a currency.
     *
     * @param account {@link Account} object for which balance is desired
     * @param ccy     ISO code of the lookup currency e.g USD
     * @return Current Account Balance the currency specified
     */
    public BigDecimal getCurrentBalance(Account account, String ccy) {
        return getAccountBalance(account, ccy)
            .map(AccountBalance::getCashBalance)
            .orElse(BigDecimal.ZERO);
    }

    /**
     * Change the balance on the account on a currency
     *
     * @param account {@link Account} being updated
     * @param ccy     Currency for which balance update requested
     * @param change  delta for balance amount
     * @return {@link AccountBalance} with updated balance
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Optional<AccountBalance> updateAccountBalance(Account account, String ccy, BigDecimal change) {
        return getAccountBalance(account, ccy).map(accountBalance -> {
            logger.info("Current Account Balance {}", accountBalance);
            logger.info("change requested: {}", change);
            accountBalance.setCashBalance(accountBalance.getCashBalance().add(change));
            return balanceRepo.saveAndFlush(accountBalance);
        });
    }

    /**
     * Change the balance on the account on a currency
     *
     * @param accountBalance {@link AccountBalance} being updated
     * @param change         delta for balance amount
     * @return {@link AccountBalance} with updated balance
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Optional<AccountBalance> updateAccountBalance(Optional<AccountBalance> accountBalance, BigDecimal change) {
        return accountBalance.map(ab -> {
            logger.info("Curr Account Balance {}", ab);
            logger.info("Delta requested: {}", change);
            ab.setCashBalance(ab.getCashBalance().add(change));
            return balanceRepo.saveAndFlush(ab);
        });
    }

    /**
     * Find an active account using account id
     *
     * @param accountId account id of the @{@link Account} object
     * @return <code>{@link Optional<Account>}</code> if the account is active
     */
    public Optional<Account> getValidAccount(Integer accountId) {
        return accountRepo.findById(accountId)
            .filter(Account::getActive);
    }

    public WebOpsResult<List<Account>> getAccountByLastName(String lastName) {
        List<Account> acct = accountRepo.findByLastName(lastName);
        if (acct.isEmpty()) {
            Map<String, String> errors = new HashMap<>();
            errors.put("account.lastName", String.format("No account with last name %s", lastName));
            return WebOpsResult.failureOf(errors);
        }
        return WebOpsResult.successOf(acct);
    }

}
