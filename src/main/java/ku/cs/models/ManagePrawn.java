package ku.cs.models;

public class ManagePrawn {
    private String id;
    private boolean giveFoodStatus;
    private boolean givePillsStatus;
    private String manageStatus;
    private String measureWeight;
    private String prawnID;
    private String pondID;

    public ManagePrawn(String id, boolean giveFoodStatus, boolean givePillsStatus, String measureWeight, String manageStatus, String prawnID, String pondID) {
        this.id = id;
        this.giveFoodStatus = giveFoodStatus;
        this.givePillsStatus = givePillsStatus;
        this.measureWeight = measureWeight;
        this.manageStatus = manageStatus;
        this.prawnID = prawnID;
        this.pondID = pondID;
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

    public String getPrawnID() {
        return prawnID;
    }

    public void setPrawnID(String prawnID) {
        this.prawnID = prawnID;
    }

    public String getPondID() {
        return pondID;
    }

    public void setPondID(String pondID) {
        this.pondID = pondID;
    }

    public void setManageStatus(String manageStatus) {
        this.manageStatus = manageStatus;
    }

    public boolean checkPrawnId(String id){
        return this.id.equals(id);
    }
}
