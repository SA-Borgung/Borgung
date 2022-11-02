package ku.cs.models;

public class QC {
    private String id, requirement, time, manageStatus, note, employeeID,pondID;

    public QC(String id, String requirement, String time, String manageStatus, String note, String employeeID, String pondID) {
        this.id = id;
        this.requirement = requirement;
        this.time = time;
        this.manageStatus = manageStatus;
        this.note = note;
        this.employeeID = employeeID;
        this.pondID = pondID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManageStatus() {
        return manageStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setManageStatus(String manageStatus) {
        this.manageStatus = manageStatus;
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

    public boolean checkID(String id) {
        return this.id.equals(id);
    }
}
