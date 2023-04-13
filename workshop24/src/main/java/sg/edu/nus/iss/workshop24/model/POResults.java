package sg.edu.nus.iss.workshop24.model;

import java.time.LocalDate;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

public class POResults {
    private int orderId;
    private LocalDate orderDate;
    private String customerName;
    private String shipAddress;
    private String product;
    private double unitPrice;
    private double discount;
    private int quantity;

    public POResults() {
    }

    public POResults(int orderId, LocalDate orderDate, String customerName, String shipAddress, String product,
            double unitPrice, double discount, int quantity) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.shipAddress = shipAddress;
        this.product = product;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "POResults [orderId=" + orderId + ", orderDate=" + orderDate + ", customerName=" + customerName
                + ", shipAddress=" + shipAddress + ", product=" + product + ", unitPrice=" + unitPrice + ", discount="
                + discount + ", quantity=" + quantity + "]";
    }

    public static POResults createFromResults(SqlRowSet rs) {
        POResults po = new POResults();
        po.setOrderId(rs.getInt("orderId"));
        po.setOrderDate(rs.getDate("orderDate").toLocalDate());
        po.setCustomerName(rs.getString("customerName"));
        po.setShipAddress(rs.getString("shipAddress"));
        po.setProduct(rs.getString("product"));
        po.setUnitPrice(rs.getDouble("unitPrice"));
        po.setDiscount(rs.getDouble("discount"));
        po.setQuantity(rs.getInt("quantity"));
        return po;
    }

    public JsonObjectBuilder toJSONObject() {
        return Json.createObjectBuilder()
                .add("orderId", this.getOrderId())
                .add("orderDate", this.getOrderDate().toString())
                .add("customerName", this.getCustomerName())
                .add("shipAddress", this.getShipAddress())
                .add("product", this.getProduct())
                .add("unitPrice", this.getUnitPrice())
                .add("discount", this.getUnitPrice())
                .add("quantity", this.getQuantity());
    }

    public JsonObjectBuilder toJSONObjectCustomer() {
        return Json.createObjectBuilder()
                .add("orderId", this.getOrderId())
                .add("orderDate", this.getOrderDate().toString())
                .add("customerName", this.getCustomerName())
                .add("shipAddress", this.getShipAddress());
    }

    public JsonObjectBuilder toJSONObjectProduct() {
        return Json.createObjectBuilder()
                .add("product", this.getProduct())
                .add("unitPrice", this.getUnitPrice())
                .add("discount", this.getUnitPrice())
                .add("quantity", this.getQuantity());
    }

}
