package gouri.ibk.interactivetrader.bl;

import gouri.ibk.interactivetrader.model.TraderPositionKey;
import gouri.ibk.interactivetrader.repo.PositionRepo;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Date;

@Named
public class PositionFacade {

    @Inject
    PositionRepo positionRepo;

    public BigDecimal getTraderPositionByDate(int traderId, String ticker, Date tradeDate) {
        TraderPositionKey key = new TraderPositionKey()
            .setAccountId(traderId)
            .setTicker(ticker)
            .setReportDate(new java.sql.Date(tradeDate.getTime()));
        return positionRepo
            .findById(key)
            .map(t -> t.getMarketValue())
            .orElse(new BigDecimal(0.0));
    }


}
