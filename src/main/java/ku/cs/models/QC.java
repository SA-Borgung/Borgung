package ku.cs.models;

public class QC {
    private String id,requirement,time,employeeID,pondID;

    public QC(String id, String requirement, String time, String employeeID, String pondID) {
        this.id = id;
        this.requirement = requirement;
        this.time = time;
        this.employeeID = employeeID;
        this.pondID = pondID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
