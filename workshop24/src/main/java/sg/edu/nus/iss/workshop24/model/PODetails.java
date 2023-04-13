package sg.edu.nus.iss.workshop24.model;

public class PODetails {
    private int id;
    private String productName;
    private double unitPrice;
    private double discount;

    public PODetails() {
    }

    public PODetails(int id, String productName, double unitPrice, double discount) {
        this.id = id;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    @Override
    public String toString() {
        return "PODetails [id=" + id + ", productName=" + productName + ", unitPrice=" + unitPrice + ", discount="
                + discount + "]";
    }

}
