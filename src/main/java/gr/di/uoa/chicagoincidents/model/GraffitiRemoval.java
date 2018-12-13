package gr.di.uoa.chicagoincidents.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "graffiti_removals")
public class GraffitiRemoval extends ServiceRequest {

    private String SSA;

    private String surfaceType;

    private String graffitiLocation;


    public GraffitiRemoval() {
    }

    public GraffitiRemoval(Date creationDate,
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
                           String SSA,
                           String surfaceType,
                           String graffitiLocation) {
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
        this.setSSA(SSA);
        this.setSurfaceType(surfaceType);
        this.setGraffitiLocation(graffitiLocation);
    }


    public String getSSA() {
        return SSA;
    }

    public void setSSA(String SSA) {
        this.SSA = SSA;
    }


    public String getSurfaceType() {
        return surfaceType;
    }

    public void setSurfaceType(String surfaceType) {
        this.surfaceType = surfaceType;
    }


    public String getGraffitiLocation() {
        return graffitiLocation;
    }

    public void setGraffitiLocation(String graffitiLocation) {
        this.graffitiLocation = graffitiLocation;
    }

    public String toCsvLine(int id) {
        return encapsulateWithQuotes(this.SSA) + ";"
          + encapsulateWithQuotes(this.graffitiLocation) + ";"
          + encapsulateWithQuotes(this.surfaceType) + ";"
          + id +"\n";
    }

    public String superToCsvLine(int id) {
        return super.toCsvLine(id);
    }
}
