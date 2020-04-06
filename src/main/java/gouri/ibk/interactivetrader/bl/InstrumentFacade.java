package gouri.ibk.interactivetrader.bl;

import gouri.ibk.interactivetrader.model.InstrumentPrice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Named
public class InstrumentFacade {

    @Inject
    JdbcTemplate template;

    public List<InstrumentPrice> getCurrentPrice(String[] tickers) {
        String query = "SELECT Top 1 * \n" +
            "FROM INSTRUMENT_PRICE_HISTORY \n" +
            "WHERE TICKER IN (?) \n" +
            "GROUP BY REPORT_DATE, TICKER\n" +
            "ORDER BY LAST_UPDATED DESC ";

        return template.query(query, tickers, new RowMapper<InstrumentPrice>() {
            @Override
            public InstrumentPrice mapRow(ResultSet rs, int rowNum) throws SQLException {
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

}
