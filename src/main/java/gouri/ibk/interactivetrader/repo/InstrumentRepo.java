package gouri.ibk.interactivetrader.repo;

import gouri.ibk.interactivetrader.model.InstrumentPrice;
import gouri.ibk.interactivetrader.model.InstrumentPriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface InstrumentRepo extends JpaRepository<InstrumentPriceHistory, Integer> {

}
