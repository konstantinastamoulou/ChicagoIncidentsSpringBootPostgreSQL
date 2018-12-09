package gr.di.uoa.chicagoincidents.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="sanitation_code_complaints")
public class SanitationCodeComplaint extends ServiceRequest {

    private String natureOfCodeViolation;


    public SanitationCodeComplaint() {

    }

    public SanitationCodeComplaint(Date creationDate,
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
                                   String natureOfCodeViolation) {
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
        this.setNatureOfCodeViolation(natureOfCodeViolation);
    }

    public String getNatureOfCodeViolation() {
        return natureOfCodeViolation;
    }

    public void setNatureOfCodeViolation(String natureOfCodeViolation) {
        this.natureOfCodeViolation = natureOfCodeViolation;
    }

}
