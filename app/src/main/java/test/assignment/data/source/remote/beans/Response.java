package test.assignment.data.source.remote.beans;

public class Response {
    public static final String SUCCESS = "success";

    protected String status;
    protected String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
