package gr.di.uoa.chicagoincidents.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="tree_debris")
public class TreeDebris extends ServiceRequest{

    private String currentActivity;

    private String mostRecentAction;

    private String debrisLocation;


    public TreeDebris() {
    }

    public TreeDebris(Date creationDate,
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
                      String debrisLocation) {
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
        this.currentActivity = currentActivity;
        this.mostRecentAction = mostRecentAction;
        this.debrisLocation = debrisLocation;
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


    public String getDebrisLocation() {
        return debrisLocation;
    }

    public void setDebrisLocation(String debrisLocation) {
        this.debrisLocation = debrisLocation;
    }

}
