package gouri.ibk.interactivetrader.repo;

import gouri.ibk.interactivetrader.model.OrderMaster;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface OrderMasterRepo extends JpaRepository<OrderMaster, Integer> {

    List<OrderMaster> findTop5ByAccountByTraderId_AccountId(Integer accountId, Sort sort);

    int countByTradeDateAndAccountByTraderId_AccountId(Date date, int id);

    @Query(value="select count(order_id) from order_master where trader_id = :id and executed_time >= current_date", nativeQuery=true)
    int getTradeCountForToday(int id);

    @Query(value="select * from order_master where trader_id = :id and formatdatetime(executed_time, 'yyyy-MM-dd') = :date",
        nativeQuery=true)
    List<OrderMaster> findOrdersByTraderAndDate(int id, Date date);
}
