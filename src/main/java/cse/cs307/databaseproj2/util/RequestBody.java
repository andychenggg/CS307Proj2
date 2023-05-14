package cse.cs307.databaseproj2.util;

public class RequestBody {
    private String requestType;
    private String page;

    public RequestBody(String requestType, String page) {
        this.requestType = requestType;
        this.page = page;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
