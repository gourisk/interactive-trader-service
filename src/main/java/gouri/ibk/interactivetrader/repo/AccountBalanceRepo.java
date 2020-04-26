package gouri.ibk.interactivetrader.repo;

import gouri.ibk.interactivetrader.model.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountBalanceRepo extends JpaRepository<AccountBalance, Integer> {
}
