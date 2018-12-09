package gr.di.uoa.chicagoincidents.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="garbage_carts")
public class GarbageCart extends ServiceRequest{

    private String currentActivity;

    private Integer blackCardsDelivered;

    private String mostRecentAction;

    private String SSA;

    public GarbageCart() {

    }

    public GarbageCart(Date creationDate,
                       String status,
                       Date completionDate,
                       String serviceRequestNumber,
                       String serviceRequestType,
                       String streetAddress,
                       Integer zipCode,
                       Double xCoordinate,
                       Double yCoordinate,
                       Integer ward,
                       Integer policeDistrict,
                       Integer communityArea,
                       Double latitude,
                       Double longitude,
                       String location,
                       String currentActivity,
                       String mostRecentAction,
                       String SSA,
                       Integer blackCardsDelivered) {

        super(creationDate,
                status,
                completionDate,
                serviceRequestNumber,
                serviceRequestType,
                streetAddress,
                zipCode,
                xCoordinate,
                yCoordinate,
                ward,
                policeDistrict,
                communityArea,
                latitude,
                longitude,
                location);
        this.setCurrentActivity(currentActivity);
        this.setMostRecentAction(mostRecentAction);
        this.setSSA(SSA);
        this.setBlackCardsDelivered(blackCardsDelivered);
    }

    public String getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(String currentActivity) {
        this.currentActivity = currentActivity;
    }


    public String getMostRecentAction() {
        return mostRecentAction;
    }

    public void setMostRecentAction(String mostRecentAction) {
        this.mostRecentAction = mostRecentAction;
    }


    public String getSSA() {
        return SSA;
    }

    public void setSSA(String SSA) {
        this.SSA = SSA;
    }


    public Integer getBlackCardsDelivered() {
        return blackCardsDelivered;
    }

    public void setBlackCardsDelivered(Integer blackCardsDelivered) {
        this.blackCardsDelivered = blackCardsDelivered;
    }


}
