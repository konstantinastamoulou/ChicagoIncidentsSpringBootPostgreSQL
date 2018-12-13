package gr.di.uoa.chicagoincidents.model.datatypes;

import java.util.Date;

public class RequestCountPerDayForDateRange {

    private Integer requestCount;

    private Date creationDate;

    public RequestCountPerDayForDateRange() {}

    public RequestCountPerDayForDateRange(Integer requestCount, Date creationDate) {
        this.requestCount = requestCount;
        this.creationDate = creationDate;
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
