package gouri.ibk.interactivetrader.repo;

import gouri.ibk.interactivetrader.model.TraderPosition;
import gouri.ibk.interactivetrader.model.TraderPositionKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepo extends JpaRepository<TraderPosition, TraderPositionKey> {
}
