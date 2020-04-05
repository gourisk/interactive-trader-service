package gouri.ibk.interactivetrader.repo;

import gouri.ibk.interactivetrader.model.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMasterRepo extends JpaRepository<OrderMaster, Integer> {

    List<OrderMaster> findByAccountByTraderId_AccountId(Integer accountId);
}
