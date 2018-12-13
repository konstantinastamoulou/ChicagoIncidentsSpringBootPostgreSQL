package gr.di.uoa.chicagoincidents.model.datatypes;

public class RequestCountPerType {

    private Integer requestCount;

    private String requestType;

    public RequestCountPerType() {
    }

    public RequestCountPerType(Integer requestCount, String requestType) {
        this.requestCount = requestCount;
        this.requestType = requestType;
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }


    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

}
