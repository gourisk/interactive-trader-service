package gouri.ibk.interactivetrader.web;

import gouri.ibk.interactivetrader.model.InstrumentPrice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InstrumentController {

    /**
     * this method get the list of the tickes  and provides the current price of each of the ticker
     *
     * @param tickers
     * @return
     */
    @GetMapping
    public List<InstrumentPrice> getMyPrices(String[] tickers) {
        return null;
    }
    
}
