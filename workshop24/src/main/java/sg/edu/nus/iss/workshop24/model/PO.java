package sg.edu.nus.iss.workshop24.model;

import java.time.LocalDate;

import jakarta.servlet.http.HttpServletRequest;

public class PO {
    private int id;
    private LocalDate orderDate;
    private String customerName;
    private String shipAddress;
    private String notes;
    private double tax;

    public PO() {
    }

    public PO(int id, LocalDate orderDate, String customerName, String shipAddress, String notes, double tax) {
        this.id = id;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.shipAddress = shipAddress;
        this.notes = notes;
        this.tax = tax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "PO [id=" + id + ", orderDate=" + orderDate + ", customerName=" + customerName + ", shipAddress="
                + shipAddress + ", notes=" + notes + ", tax=" + tax + "]";
    }

    public static PO createFromRequest(HttpServletRequest request) {
        PO po = new PO();
        po.setCustomerName(request.getParameter("name"));
        po.setShipAddress(request.getParameter("ship_address"));
        po.setNotes(request.getParameter("notes"));
        return po;
    }

}
