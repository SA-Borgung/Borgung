package ku.cs.models;

public class PreparePond {
    private String prepareID,status,employeeID,pondID;

    public PreparePond(String prepareID, String status, String employeeID, String pondID) {
        this.prepareID = prepareID;
        this.status = status;
        this.employeeID = employeeID;
        this.pondID = pondID;
    }

    public String getPrepareID() {
        return prepareID;
    }

    public void setPrepareID(String prepareID) {
        this.prepareID = prepareID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getPondID() {
        return pondID;
    }

    public void setPondID(String pondID) {
        this.pondID = pondID;
    }
}
