package ku.cs.models;

public class VendorOrder {

    String id,sellerName,employeeID,status;
    int amount;

    public VendorOrder(String id, int amount, String sellerName, String status, String employeeID) {
        this.id = id;
        this.amount = amount;
        this.sellerName = sellerName;
        this.employeeID = employeeID;
        this.status = status;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean checkId(String id) {
        return this.id.equals(id);
    }
}
