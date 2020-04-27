package gouri.ibk.interactivetrader.bl;

import gouri.ibk.interactivetrader.repo.AccountRepo;
import gouri.ibk.interactivetrader.repo.OrderMasterRepo;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Date;

@Named
public class PositionFacade {

    @Inject
    OrderMasterRepo orderRepo;

    @Inject
    AccountRepo accountRepo;

    public BigDecimal calculateTraderPLForToday(int traderId) {
        return null;
    }

}
