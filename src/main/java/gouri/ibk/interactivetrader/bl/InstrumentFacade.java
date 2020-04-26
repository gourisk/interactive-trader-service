package gouri.ibk.interactivetrader.bl;

import gouri.ibk.interactivetrader.model.InstrumentMaster;
import gouri.ibk.interactivetrader.model.InstrumentPrice;
import gouri.ibk.interactivetrader.repo.InstrumentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Named
public class InstrumentFacade {

    Logger logger = LoggerFactory.getLogger(InstrumentFacade.class);

    @Inject
    JdbcTemplate template;

    @Inject
    DataSource dataSource;

    @Inject
    InstrumentRepo instrumentRepo;

    public Optional<InstrumentMaster> getValidTicker(String ticker) {
        return instrumentRepo.findById(ticker);
    }

    public List<InstrumentPrice> getCurrentPrice(String ticker) {
        String query = "SELECT Top 1 * FROM INSTRUMENT_PRICE_HISTORY WHERE TICKER = ? ORDER BY LAST_UPDATED DESC ";

        return template.query(query, new Object[]{ticker}, new RowMapper<InstrumentPrice>() {
            @Override
            public InstrumentPrice mapRow(ResultSet rs, int rowNum) throws SQLException {
                logger.info("resultset: {}", rs);
                InstrumentPrice price = new InstrumentPrice()
                    .setTicker(rs.getString("TICKER"))
                    .setReportDate(rs.getDate("REPORT_DATE"))
                    .setMidPrice(rs.getBigDecimal("MID_PRICE"))
                    .setBidPrice(rs.getBigDecimal("BID_PRICE"))
                    .setAskPrice(rs.getBigDecimal("ASK_PRICE"));
                return price;
            }
        });
    }

    public List<InstrumentPrice> getCurrentPriceMulti(String[] tickers) {
        String query = "SELECT p1.* \n" +
            "FROM INSTRUMENT_PRICE_HISTORY p1\n" +
            "INNER JOIN (\n" +
            "SELECT TICKER, MAX(LAST_UPDATED) LAST_UPDATED \n" +
            "FROM INSTRUMENT_PRICE_HISTORY \n" +
            "WHERE TICKER IN ({tickers}) \n" +
            "GROUP BY TICKER \n" +
            ") p2 ON  p1.TICKER = p2.TICKER AND p1.LAST_UPDATED =p2.LAST_UPDATED";

        String tickersWithQuote = getQuotedParam(tickers);
        String runTimeQuery = query.replace("{tickers}", tickersWithQuote);
        logger.info("query = {}", runTimeQuery);

        return template.query(runTimeQuery, new RowMapper<InstrumentPrice>() {
            @Override
            public InstrumentPrice mapRow(ResultSet rs, int rowNum) throws SQLException {
                logger.info("result set: {}", rs);
                InstrumentPrice price = new InstrumentPrice()
                    .setTicker(rs.getString("TICKER"))
                    .setReportDate(rs.getDate("REPORT_DATE"))
                    .setMidPrice(rs.getBigDecimal("MID_PRICE"))
                    .setBidPrice(rs.getBigDecimal("BID_PRICE"))
                    .setAskPrice(rs.getBigDecimal("ASK_PRICE"));
                return price;
            }
        });
    }

    private String getQuotedParam(String[] tickers) {
        String tickersWithQuote = Arrays.stream(tickers)
            .map(el -> "'" + el + "'")
            .collect(Collectors.joining(","));
        logger.info("tickers with Quote: {}", tickersWithQuote);
        return tickersWithQuote;
    }

    public InstrumentPrice publishPrice(String ticker) {
        List<InstrumentPrice> instrumentPrices = getCurrentPrice(ticker);
        logger.info("price found as : {}", instrumentPrices);
        InstrumentPrice instrumentPrice = instrumentPrices.size() == 0 ? null : instrumentPrices.get(0);
        logger.info("{} = {}", ticker, instrumentPrice == null ? null : instrumentPrice.getMidPrice());
        Double priceChange = new Random().nextInt(20) - 10.5;
        BigDecimal midPrice = instrumentPrice.getMidPrice().add(new BigDecimal(priceChange));
        BigDecimal bidPrice = instrumentPrice.getBidPrice().add(new BigDecimal(priceChange));
        BigDecimal askPrice = instrumentPrice.getAskPrice().add(new BigDecimal(priceChange));
        String updateQuery = "INSERT INTO INSTRUMENT_PRICE_HISTORY VALUES(SYSDATE(), ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        Object[] params = new Object[]{ticker, bidPrice, askPrice, midPrice};
        template.update(updateQuery, params);
        return new InstrumentPrice()
            .setTicker(ticker)
            .setBidPrice(bidPrice)
            .setAskPrice(askPrice)
            .setMidPrice(midPrice);

    }

}
