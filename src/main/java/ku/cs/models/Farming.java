package ku.cs.models;

public class Farming {

    private String pondID;
    private int round;
    private int prawnAmount;
    private String prawnID;
    private String getDate;
    private String sellDate;
    private String orderID;
    private String vendorOrderID;
    private String pondStatus;

    public Farming(String pondID, int round, int prawnAmount, String prawnID, String getDate, String sellDate, String orderID, String vendorOrderID, String pondStatus) {
        this.pondID = pondID;
        this.round = round;
        this.prawnAmount = prawnAmount;
        this.prawnID = prawnID;
        this.getDate = getDate;
        this.sellDate = sellDate;
        this.orderID = orderID;
        this.vendorOrderID = vendorOrderID;
        this.pondStatus = pondStatus;
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

    public String getPondStatus() {
        return pondStatus;
    }

    public void setPondStatus(String pondStatus) {
        this.pondStatus = pondStatus;
    }

    public boolean addFarmingInputCheck(){
        if(getDate == null){
            return false;
        }
        if(round < 0){
            return false;
        }
        return true;
    }
}
