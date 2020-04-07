package gouri.ibk.interactivetrader.repo;

import gouri.ibk.interactivetrader.model.OrderMaster;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface OrderMasterRepo extends JpaRepository<OrderMaster, Integer> {

    List<OrderMaster> findTop5ByAccountByTraderId_AccountId(Integer accountId, Sort sort);

    int countByTradeDateAndAccountByTraderId_AccountId(Date date, int id);
}
