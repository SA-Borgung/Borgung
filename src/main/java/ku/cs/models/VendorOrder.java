package ku.cs.models;

public class VendorOrder {

    String id,amount,sellerName,employeeID;

    public VendorOrder(String id, String amount, String sellerName, String employeeID) {
        this.id = id;
        this.amount = amount;
        this.sellerName = sellerName;
        this.employeeID = employeeID;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}
