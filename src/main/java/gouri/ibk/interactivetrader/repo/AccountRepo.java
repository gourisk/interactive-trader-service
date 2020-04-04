package gouri.ibk.interactivetrader.repo;

import gouri.ibk.interactivetrader.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AccountRepo extends JpaRepository<Account, Integer> {

    Account findFirstByEmail(String email);

    Account findFirstByFirstNameAndLastName(String first, String last);

    List<Account> findByLastName(String lastName);

}
