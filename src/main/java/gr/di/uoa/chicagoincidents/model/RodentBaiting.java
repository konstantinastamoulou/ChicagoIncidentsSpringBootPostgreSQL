package gr.di.uoa.chicagoincidents.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="rodent_baitings")
public class RodentBaiting extends ServiceRequest{

    private String currentActivity;

    private String mostRecentAction;

    private Integer numOfPremisesBaited;

    private Integer numOfPremisesWithGarbage;

    private Integer numOfPremisesWithRats;


    public RodentBaiting() {
    }

    public RodentBaiting(Date creationDate,
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
                         Integer numOfPremisesBaited,
                         Integer numOfPremisesWithGarbage,
                         Integer numOfPremisesWithRats) {
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
        this.setNumOfPremisesBaited(numOfPremisesBaited);
        this.setNumOfPremisesWithGarbage(numOfPremisesWithGarbage);
        this.setNumOfPremisesWithRats(numOfPremisesWithRats);

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


    public Integer getNumOfPremisesBaited() {
        return numOfPremisesBaited;
    }

    public void setNumOfPremisesBaited(Integer numOfPremisesBaited) {
        this.numOfPremisesBaited = numOfPremisesBaited;
    }


    public Integer getNumOfPremisesWithGarbage() {
        return numOfPremisesWithGarbage;
    }

    public void setNumOfPremisesWithGarbage(Integer numOfPremisesBaited) {
        this.numOfPremisesWithGarbage = numOfPremisesWithGarbage;
    }


    public Integer getNumOfPremisesWithRats() {
        return numOfPremisesWithRats;
    }

    public void setNumOfPremisesWithRats(Integer numOfPremisesWithRats) {
        this.numOfPremisesWithRats = numOfPremisesWithRats;
    }

    public String toCsvLine(int id) {
        return encapsulateWithQuotes(this.currentActivity) + ";"
          + encapsulateWithQuotes(this.mostRecentAction) + ";"
          + encapsulateWithQuotes(this.numOfPremisesBaited == null ? 0 : this.numOfPremisesBaited) + ";"
          + encapsulateWithQuotes(this.numOfPremisesWithGarbage == null ? 0 : this.numOfPremisesWithGarbage) + ";"
          + encapsulateWithQuotes(this.numOfPremisesWithRats == null ? 0 : this.numOfPremisesWithRats) + ";"
          + id +"\n";
    }

    public String superToCsvLine(int id) {
        return super.toCsvLine(id);
    }

}
