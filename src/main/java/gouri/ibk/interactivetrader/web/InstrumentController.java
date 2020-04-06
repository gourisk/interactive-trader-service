package gouri.ibk.interactivetrader.web;

import gouri.ibk.interactivetrader.bl.InstrumentFacade;
import gouri.ibk.interactivetrader.model.InstrumentPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@CrossOrigin({"http://localhost:3000", "http://localhost:5000"})
public class InstrumentController {

    public static Logger logger = LoggerFactory.getLogger(InstrumentController.class);

    @Inject
    InstrumentFacade instrumentFacade;


    /**
     * this method get the list of the tickers  and provides the current price of each of the ticker
     *
     * @param tickers
     * @return
     */
    @GetMapping("/get_instrument_prices")
    public List<InstrumentPrice> getMyPrices(@RequestParam("tickers") String tickerParam) {
        String[] tickers = tickerParam.split(",");
        List<InstrumentPrice> instrumentPrices = instrumentFacade.getCurrentPriceMulti(tickers);
        return instrumentPrices;
    }

    @PostMapping(value = "/publish_price")
    public Object[] publishPrice(@RequestBody InstrumentPrice instPrice) {
        logger.info("Price update requested for: {}", instPrice);

        Object[] newPrice = instrumentFacade.publishPrice(instPrice.getTicker());
        return newPrice;
    }
}
