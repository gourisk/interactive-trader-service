package gouri.ibk.interactivetrader.repo;

import gouri.ibk.interactivetrader.model.InstrumentPriceHistory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Generic JDBC based repository to interact with database
 * This usually takes care of complex query scenarios usually easily handled using JDBC.
 * For standard JPA calls please refer to Spring data JPA sub interfaces defined.
 */
@Named
public class AppJdbcRepo {

    static final String QUERY_FOR_LATEST_PRICE_BY_TICKERS = "select h.* \n" +
        "from INSTRUMENT_PRICE_HISTORY  h\n" +
        "INNER JOIN (SELECT TICKER, MAX(LAST_UPDATED) LAST_UPDATED\n" +
        "FROM INSTRUMENT_PRICE_HISTORY \n" +
        "WHERE TICKER IN (:1)  \n" +
        "group by TICKER) lh ON h.ticker = lh.ticker and h.LAST_UPDATED = lh.LAST_UPDATED";

    @Inject
    DataSource dataSource;

    private JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    public Map<String, InstrumentPriceHistory> getLatestPrices(Set<String> tickers) {
        List<InstrumentPriceHistory> prices = getJdbcTemplate().query(QUERY_FOR_LATEST_PRICE_BY_TICKERS, new Object[]{tickers.toArray()},
            (rs, rowNum) -> new InstrumentPriceHistory()
                .setReportDate(rs.getDate("REPORT_DATE"))
                .setBidPrice(rs.getBigDecimal("BID_PRICE"))
                .setAskPrice(rs.getBigDecimal("ASK_PRICE"))
                .setMidPrice(rs.getBigDecimal("MID_PRICE"))
                .setLastUpdated(rs.getTimestamp("LAST_UPDATED"))
                .setTicker(rs.getString("TICKER")));

        return prices.stream().collect(Collectors.toMap(InstrumentPriceHistory::getTicker, Function.identity()));
    }

}
