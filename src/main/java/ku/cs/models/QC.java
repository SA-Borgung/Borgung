package ku.cs.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class QC {
    private String id, requirement, time, manageStatus, note, employeeID, pondID;
    private Connection connection;
    private PreparedStatement pst;
    private String databaseName = "borgung";

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

    public void insertToSql() {
        try {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                System.out.println(e);
            }
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            connection = DriverManager.getConnection(url , "root","");
            pst = connection.prepareStatement("Insert into qc(Q_ID,Q_REQUIREMENT,Q_TIME,Q_STATUS,Q_NOTE,E_ID,W_NO)values(?,?,?,?,?,?,?)");
            pst.setString(1, this.id);
            pst.setString(2, this.requirement);
            pst.setString(3, this.time);
            pst.setString(4, this.manageStatus);
            pst.setString(5, this.note);
            pst.setString(6, this.employeeID);
            pst.setString(7, this.pondID);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateToSql() {
        try {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                System.out.println(e);
            }
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            connection = DriverManager.getConnection(url , "root","");
            pst = connection.prepareStatement("UPDATE qc SET Q_REQUIREMENT = ?,Q_TIME = ?,Q_STATUS= ? ,Q_NOTE= ? ,E_ID= ? ,W_NO= ? WHERE Q_ID=?");
            pst.setString(1, this.requirement);
            pst.setString(2, this.time);
            pst.setString(3, this.manageStatus);
            pst.setString(4, this.note);
            pst.setString(5, this.employeeID);
            pst.setString(6, this.pondID);
            pst.setString(7, this.id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
