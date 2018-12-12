package gr.di.uoa.chicagoincidents.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="pot_holes")
public class PotHole extends ServiceRequest{

    private String currentActivity;

    private String mostRecentAction;

    private String SSA;

    private Integer numOfPotholes;

    public PotHole() {
    }

    public PotHole(Date creationDate,
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
                   Integer numOfPotholes) {
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
        this.setNumOfPotholes(numOfPotholes);
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


    public Integer getNumOfPotholes() {
        return numOfPotholes;
    }

    public void setNumOfPotholes(Integer numOfPotholes) {
        this.numOfPotholes = numOfPotholes;
    }

    public String toCsvLine(int id) {
        return encapsulateWithQuotes(this.SSA) + ";" + encapsulateWithQuotes(this.currentActivity) + ";"
          + encapsulateWithQuotes(this.mostRecentAction) + ";" + encapsulateWithQuotes(this.numOfPotholes == null ? 0 : this.numOfPotholes) + ";"
          + id +"\n";
    }

    public String superToCsvLine(int id) {
        return super.toCsvLine(id);
    }

}
