package ku.cs.models;

public class ManagePrawn {
    private String id;
    private int giveFoodStatus;
    private int givePillsStatus;
    private String measureWeight;
    private String prawnID;
    private String pondID;

    public ManagePrawn(String id, int giveFoodStatus, int givePillsStatus, String measureWeight, String prawnID, String pondID) {
        this.id = id;
        this.giveFoodStatus = giveFoodStatus;
        this.givePillsStatus = givePillsStatus;
        this.measureWeight = measureWeight;
        this.prawnID = prawnID;
        this.pondID = pondID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGiveFoodStatus() {
        return giveFoodStatus;
    }

    public void setGiveFoodStatus(int giveFoodStatus) {
        this.giveFoodStatus = giveFoodStatus;
    }

    public int getGivePillsStatus() {
        return givePillsStatus;
    }

    public void setGivePillsStatus(int givePillsStatus) {
        this.givePillsStatus = givePillsStatus;
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
}
