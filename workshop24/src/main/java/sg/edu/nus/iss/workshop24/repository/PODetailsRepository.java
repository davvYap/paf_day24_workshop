package sg.edu.nus.iss.workshop24.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop24.model.Product;
import sg.edu.nus.iss.workshop24.utility.PODetailsUtility;

import static sg.edu.nus.iss.workshop24.repository.DBQueries.*;

@Repository
public class PODetailsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PODetailsUtility poUtility;

    public static double tax;

    public List<Product> getAllProducts() {
        String query = GET_ALL_PRODUCTS;
        final List<Product> products = jdbcTemplate.query(
                query,
                ((rs, rowNum) -> {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setStandardPrice(rs.getDouble("standard_price"));
                    product.setDiscount(rs.getDouble("discount"));
                    return product;
                }));
        return products;
    }

    // unit price and discount
    public int[] insertPurchaseOrderDetails(List<Product> products, int orderId) {

        // to get product price and discount
        List<Product> allProducts = getAllProducts();
        for (Product ap : allProducts) {
            for (Product p : products) {
                if (ap.getName().equalsIgnoreCase(p.getName())) {
                    p.setStandardPrice(ap.getStandardPrice());
                    p.setDiscount(ap.getDiscount());
                }
            }
        }

        // to get PO tax amount based on order_id
        SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_PO_TAX, orderId);
        while (rs.next()) {
            PODetailsRepository.tax = rs.getDouble("tax");
        }

        List<Object[]> data = products.stream()
                .map(product -> {
                    Object[] obj = new Object[5];
                    obj[0] = product.getName();
                    obj[1] = poUtility.getUnitPrice(product.getStandardPrice(), product.getDiscount());
                    obj[2] = product.getDiscount();
                    obj[3] = product.getQuantity();
                    obj[4] = orderId;
                    return obj;
                }).toList();
        int[] insertedRow = jdbcTemplate.batchUpdate(INSERT_PURCHASE_ORDER_DETAILS, data);
        return insertedRow;
    }

    // for selecting individual results, use Optional
}
