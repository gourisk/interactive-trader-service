package gouri.ibk.interactivetrader.web;

import gouri.ibk.interactivetrader.bl.InstrumentFacade;
import gouri.ibk.interactivetrader.model.InstrumentPrice;
import gouri.ibk.interactivetrader.model.InstrumentPriceHistory;
import gouri.ibk.interactivetrader.repo.InstrumentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
public class InstrumentController {

    public static Logger logger = LoggerFactory.getLogger(InstrumentController.class);

    @Autowired
    InstrumentRepo instrumentRepo;

    @Autowired
    InstrumentFacade instrumentFacade;


    /**
     * this method get the list of the tickes  and provides the current price of each of the ticker
     *
     * @param tickers
     * @return
     */
    @GetMapping("/get_all_instrument_prices")
    public List<InstrumentPrice> getMyPrices(@RequestBody  String[] tickers) {
        List<InstrumentPrice> instrumentPrices = instrumentFacade.getCurrentPrice(tickers);
        return instrumentPrices;
    }

    @PostMapping("/publish_price")
    public InstrumentPriceHistory publishPrice(@RequestBody String ticker) {
        List<InstrumentPrice> instrumentPrices = instrumentFacade.getCurrentPrice(new String[]{ticker});
        InstrumentPrice instrumentPrice = instrumentPrices.get(0);
        logger.info(instrumentPrice.getTicker() + " " + instrumentPrice.getMidPrice());
        Random random = new Random();
        Double currentPrice = random.nextInt(20) - 10.5;
        BigDecimal midPrice = instrumentPrice.getMidPrice().add(new BigDecimal(currentPrice));
        BigDecimal bidPrice = instrumentPrice.getBidPrice().add(new BigDecimal(currentPrice));
        BigDecimal askPrice = instrumentPrice.getAskPrice().add(new BigDecimal(currentPrice));

        InstrumentPriceHistory instrumentPriceHistory = new InstrumentPriceHistory();

        instrumentPriceHistory.setAskPrice(askPrice).setBidPrice(bidPrice).
                setMidPrice(midPrice).setReportDate(instrumentPriceHistory.getReportDate()).setAskPrice(askPrice);
        instrumentRepo.save(instrumentPriceHistory);

        return instrumentPriceHistory;
    }
}
