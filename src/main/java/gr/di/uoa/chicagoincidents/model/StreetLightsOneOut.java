package gr.di.uoa.chicagoincidents.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="street_lights_one_out")
public class StreetLightsOneOut extends ServiceRequest{

    public StreetLightsOneOut() {

    }

    public StreetLightsOneOut(Date creationDate,
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
                              String location) {
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

    }

    public String toCsvLine(int id) {
        return id +"\n";
    }

    public String superToCsvLine(int id) {
        return super.toCsvLine(id);
    }
}
