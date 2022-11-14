package ku.cs.models;

public class ManagePrawn {
    private String id;
    private boolean giveFoodStatus;
    private boolean givePillsStatus;
    private String manageStatus;
    private String measureWeight;
    private String date;
    private String farmingID;

    public ManagePrawn(String id, boolean giveFoodStatus, boolean givePillsStatus, String measureWeight, String manageStatus, String date, String farmingID) {
        this.id = id;
        this.giveFoodStatus = giveFoodStatus;
        this.givePillsStatus = givePillsStatus;
        this.measureWeight = measureWeight;
        this.manageStatus = manageStatus;
        this.date = date;
        this.farmingID = farmingID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getGiveFoodStatus() {
        return giveFoodStatus;
    }


    public void setGiveFoodStatus(boolean giveFoodStatus) {
        this.giveFoodStatus = giveFoodStatus;
    }

    public boolean getGivePillsStatus() {
        return givePillsStatus;
    }

    public void setGivePillsStatus(boolean givePillsStatus) {
        this.givePillsStatus = givePillsStatus;
    }

    public String getManageStatus() {
        return manageStatus;
    }

    public String getMeasureWeight() {
        return measureWeight;
    }

    public void setMeasureWeight(String measureWeight) {
        this.measureWeight = measureWeight;
    }

    public boolean isGiveFoodStatus() {
        return giveFoodStatus;
    }

    public boolean isGivePillsStatus() {
        return givePillsStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFarmingID() {
        return farmingID;
    }

    public void setFarmingID(String farmingID) {
        this.farmingID = farmingID;
    }

    public void setManageStatus(String manageStatus) {
        this.manageStatus = manageStatus;
    }

    public boolean checkPrawnId(String id){
        return this.id.equals(id);
    }
}
