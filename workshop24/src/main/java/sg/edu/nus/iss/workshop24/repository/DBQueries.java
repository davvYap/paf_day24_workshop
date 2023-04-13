package sg.edu.nus.iss.workshop24.repository;

public class DBQueries {

    public static final String GET_ALL_PRODUCTS = "select * from fruits_products;";

    public static final String INSERT_PURCHASE_ORDER = """
            insert into purchase_order(order_date, customer_name, ship_address, notes)
            values
            (DATE_FORMAT(DATE(SYSDATE()), '%Y-%m-%d'),?,?,?);
                """;

    public static final String INSERT_PURCHASE_ORDER_DETAILS = """
            insert into purchase_order_details(product, unit_price, discount, quantity, order_id)
            values
            (?, ?, ?, ?, ?);
                """;

    public static final String GET_PO_TAX = """
            select tax from purchase_order where order_id = ?;
                """;

    public static final String GET_ALL_PO_AND_POD = """
            select po.order_id as orderId,
            po.order_date as orderDate,
            po.customer_name as customerName,
            po.ship_address as shipAddress,
            pod.product as product,
            pod.unit_price as unitPrice,
            pod.discount as discount,
            pod.quantity as quantity
            from purchase_order po join purchase_order_details pod on po.order_id = pod.order_id;
                    """;

    public static final String GET_PO_BY_ORDER_ID = """
            select po.order_id as orderId,
            po.order_date as orderDate,
            po.customer_name as customerName,
            po.ship_address as shipAddress,
            pod.product as product,
            pod.unit_price as unitPrice,
            pod.discount as discount,
            pod.quantity as quantity
            from purchase_order po join purchase_order_details pod on po.order_id = pod.order_id
            where po.order_id = ?;

                """;
}
