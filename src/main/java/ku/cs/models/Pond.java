package ku.cs.models;

public class Pond {
    private String id;
    private int status;
    private int detail;

    public Pond(String id, int status, int detail) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDetail() {
        return detail;
    }

    public void setDetail(int detail) {
        this.detail = detail;
    }
}
