package sg.edu.nus.iss.workshop24.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop24.model.PO;
import sg.edu.nus.iss.workshop24.model.POResults;

import static sg.edu.nus.iss.workshop24.repository.DBQueries.*;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PORepositry {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int insertIntoPurchaseOrder(PO po) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement statement = conn.prepareStatement(INSERT_PURCHASE_ORDER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, po.getCustomerName());
            statement.setString(2, po.getShipAddress());
            statement.setString(3, po.getNotes());
            return statement;
        }, key);
        BigInteger primaryKey = (BigInteger) key.getKey();
        return primaryKey.intValue();
    }

    public List<POResults> getAllPo() {
        List<POResults> data = new ArrayList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_ALL_PO_AND_POD);
        while (rs.next()) {
            data.add(POResults.createFromResults(rs));
        }
        return data;
    }

    public List<POResults> getPo(int orderId) {
        List<POResults> data = new ArrayList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_PO_BY_ORDER_ID, orderId);
        while (rs.next()) {
            data.add(POResults.createFromResults(rs));
        }
        return data;
    }
}
