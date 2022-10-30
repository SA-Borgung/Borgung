package ku.cs.models;

public class Farming {

    private String pondID;
    private String round;
    private String prawnAmount;
    private String prawnID;
    private String getDate;
    private String sellDate;
    private String orderID;
    private String getPrawnID;
    private String pondStatus;

    public Farming(String pondID, String round, String prawnAmount, String prawnID, String getDate, String sellDate, String orderID, String getPrawnID, String pondStatus) {
        this.pondID = pondID;
        this.round = round;
        this.prawnAmount = prawnAmount;
        this.prawnID = prawnID;
        this.getDate = getDate;
        this.sellDate = sellDate;
        this.orderID = orderID;
        this.getPrawnID = getPrawnID;
        this.pondStatus = pondStatus;
    }

    public String getPondID() {
        return pondID;
    }

    public void setPondID(String pondID) {
        this.pondID = pondID;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getPrawnAmount() {
        return prawnAmount;
    }

    public void setPrawnAmount(String prawnAmount) {
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

    public String getGetPrawnID() {
        return getPrawnID;
    }

    public void setGetPrawnID(String getPrawnID) {
        this.getPrawnID = getPrawnID;
    }

    public String getPondStatus() {
        return pondStatus;
    }

    public void setPondStatus(String pondStatus) {
        this.pondStatus = pondStatus;
    }
}
