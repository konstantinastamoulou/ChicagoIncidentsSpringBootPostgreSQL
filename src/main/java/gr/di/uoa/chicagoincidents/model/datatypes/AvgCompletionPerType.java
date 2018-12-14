package gr.di.uoa.chicagoincidents.model.datatypes;

public class AvgCompletionPerType {

    private Integer completionTime;

    private String requestType;


    public AvgCompletionPerType() {
    }

    public AvgCompletionPerType(String requestType, Integer completionTime) {
        this.completionTime = completionTime;
        this.requestType = requestType;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }


    public Integer getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Integer completionTime) {
        this.completionTime = completionTime;
    }
}
