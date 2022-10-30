package ku.cs.models;

public class Pond {
    private String id;
    private String status;
    private String detail;

    public Pond(String id, String status, String detail) {
        this.id = id;
        this.status = status;
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}