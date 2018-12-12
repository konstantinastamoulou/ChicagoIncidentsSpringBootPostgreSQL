package gr.di.uoa.chicagoincidents.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "service_requests")
@Inheritance(strategy = InheritanceType.JOINED)
public class ServiceRequest {

    @Id
    @GeneratedValue
    private Long id;

    private Date creationDate;

    private String status;

    private Date completionDate;

    private String serviceRequestNumber;

    private String serviceRequestType;

    private String streetAddress;

    private Integer zipCode;

    private Double xCoordinate;

    private Double yCoordinate;

    private Integer ward;

    private Integer policeDistrict;

    private Integer communityArea;

    private Double latitude;

    private Double longitude;

    private String location;


    public ServiceRequest() { }

    public ServiceRequest(Date creationDate,
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
        this.setCreationDate(creationDate);
        this.setStatus(status);
        this.setCompletionDate(completionDate);
        this.setServiceRequestNumber(serviceRequestNumber);
        this.setServiceRequestType(serviceRequestType);
        this.setStreetAddress(streetAddress);
        this.setZipCode(zipCode);
        this.setXCoordinate(xCoordinate);
        this.setYCoordinate(yCoordinate);
        this.setWard(ward);
        this.setPoliceDistrict(policeDistrict);
        this.setCommunityArea(communityArea);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setLocation(location);
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }


    public String getServiceRequestNumber() {
        return serviceRequestNumber;
    }

    public void setServiceRequestNumber(String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }


    public String getServiceRequestType() {
        return serviceRequestType;
    }

    public void setServiceRequestType(String serviceRequestType) {
        this.serviceRequestType = serviceRequestType;
    }


    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }


    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }


    public Double getXCoordinateZip() {
        return xCoordinate;
    }

    public void setXCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }


    public Double getYCoordinateZip() {
        return yCoordinate;
    }

    public void setYCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }


    public Integer getWard() {
        return ward;
    }

    public void setWard(Integer ward) {
        this.ward = ward;
    }


    public Integer getPoliceDistrict() {
        return policeDistrict;
    }

    public void setPoliceDistrict(Integer policeDistrict) {
        this.policeDistrict = policeDistrict;
    }


    public Integer getCommunityArea() {
        return communityArea;
    }

    public void setCommunityArea(Integer communityArea) {
        this.communityArea = communityArea;
    }


    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }


    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String toCsvLine(int id) {
        return id + ";" + encapsulateWithQuotes(this.communityArea == null ? 0 : this.communityArea) + ";"
                + encapsulateWithQuotes(this.completionDate) + ";" + encapsulateWithQuotes(this.creationDate) + ";"
                + encapsulateWithQuotes(this.latitude == null ? 0.0 : this.latitude) + ";" + encapsulateWithQuotes(this.location) + ";"
                + encapsulateWithQuotes(this.longitude == null ? 0.0 : this.longitude) + ";" + encapsulateWithQuotes(this.policeDistrict == null ? 0 : this.policeDistrict) + ";"
                + encapsulateWithQuotes(this.serviceRequestNumber) + ";" + encapsulateWithQuotes(this.serviceRequestType) + ";"
                + encapsulateWithQuotes(this.status) + ";" + encapsulateWithQuotes(this.streetAddress) + ";"
                + encapsulateWithQuotes(this.ward == null ? 0 : this.ward) + ";" + encapsulateWithQuotes(this.xCoordinate == null ? 0.0 : this.xCoordinate) + ";"
                + encapsulateWithQuotes(this.yCoordinate == null ? 0.0 : this.yCoordinate) + ";" + encapsulateWithQuotes(this.zipCode == null ? 0 : this.zipCode) + "\n";
    }

    protected String encapsulateWithQuotes(Object value) {
        String v = value == null ? "" : value.toString();
        return v.replace(";", "");
    }
}

