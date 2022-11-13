package ku.cs.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Farming {

    private String pondID;
    private int round;
    private int prawnAmount;
    private String prawnID;
    private String getDate;
    private String sellDate;
    private String orderID;
    private String vendorOrderID;

    private Connection connection;
    private PreparedStatement pst;
    private String databaseName = "borgung";


    public Farming(String pondID, int round, int prawnAmount, String prawnID, String getDate, String sellDate, String orderID, String vendorOrderID) {

        this.pondID = pondID;
        this.round = round;
        this.prawnAmount = prawnAmount;
        this.prawnID = prawnID;
        this.getDate = getDate;
        this.sellDate = sellDate;
        this.orderID = orderID;
        this.vendorOrderID = vendorOrderID;

    }

    public String getPondID() {
        return pondID;
    }

    public void setPondID(String pondID) {
        this.pondID = pondID;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getPrawnAmount() {
        return prawnAmount;
    }

    public void setPrawnAmount(int prawnAmount) {
        this.prawnAmount = prawnAmount;
    }

    public String getPrawnID() {
        return prawnID;
    }

    public void setPrawnID(String prawnID) {
        this.prawnID = prawnID;
    }

    public String getGetDate() {
        return getDate;
    }

    public void setGetDate(String getDate) {
        this.getDate = getDate;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getVendorOrderID() {
        return vendorOrderID;
    }

    public void setVendorOrderID(String vendorOrderID) {
        this.vendorOrderID = vendorOrderID;
    }

    public boolean addFarmingInputCheck(String date, int round){
        if(date == null){
            return false;
        }
        if(round < 0){
            return false;
        }
        return true;
    }

//    public void insertToSql() {
//        try {
//            try{
//                Class.forName("com.mysql.cj.jdbc.Driver");
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            String url = "jdbc:mysql://localhost:3306/" + databaseName;
//            connection = DriverManager.getConnection(url , "root","");
//            pst = connection.prepareStatement("Insert into qc(Q_ID,Q_REQUIREMENT,Q_TIME,Q_STATUS,Q_NOTE,E_ID,W_NO)values(?,?,?,?,?,?,?)");
//            pst.setString(1, this.id);
//            pst.setString(2, this.requirement);
//            pst.setString(3, this.time);
//            pst.setString(4, this.manageStatus);
//            pst.setString(5, this.note);
//            pst.setString(6, this.employeeID);
//            pst.setString(7, this.pondID);
//            pst.executeUpdate();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
}
