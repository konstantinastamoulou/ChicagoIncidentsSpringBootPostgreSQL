package gr.di.uoa.chicagoincidents.model.datatypes;

import java.util.Date;

public class RequestCountPerDayForDateRange {

    private Long requestCount;

    private Date creationDate;

    public RequestCountPerDayForDateRange() {}

    public RequestCountPerDayForDateRange(Long requestCount, Date creationDate) {
        this.requestCount = requestCount;
        this.creationDate = creationDate;
    }

    public Long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Long requestCount) {
        this.requestCount = requestCount;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
