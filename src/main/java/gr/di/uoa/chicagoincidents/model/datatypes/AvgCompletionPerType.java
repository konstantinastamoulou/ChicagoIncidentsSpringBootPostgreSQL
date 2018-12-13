package gr.di.uoa.chicagoincidents.model.datatypes;

import java.util.Date;

public class AvgCompletionPerType {

    private Date completionTime;

    private String requestType;


    public AvgCompletionPerType() {
    }

    public AvgCompletionPerType(String requestType, Date completionTime) {
        this.completionTime = completionTime;
        this.requestType = requestType;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }


    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }
}
