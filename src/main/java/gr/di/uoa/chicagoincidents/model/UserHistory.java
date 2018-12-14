package gr.di.uoa.chicagoincidents.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user_histories")
public class UserHistory {

    @Id
    @GeneratedValue
    private Long id;

    private Long uid;

    private String apiCall;

    private Date date;

    public UserHistory(Long uid, String apiCall, Date date) {
        this.uid = uid;
        this.apiCall = apiCall;
        this.date = date;
    }

    public UserHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getApiCall() {
        return apiCall;
    }

    public void setApiCall(String apiCall) {
        this.apiCall = apiCall;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
