package gr.di.uoa.chicagoincidents.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "abandoned_vehicles")
public class AbandonedVehicle extends ServiceRequest{

    private String currentActivity;

    private String mostRecentAction;

    private String SSA;

    private String licensePlate;

    private String vehicleModel;

    private String vehicleColor;

    private String daysVehicleReportedAsParked;

    public AbandonedVehicle() {
    }

    public AbandonedVehicle(Date creationDate,
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
                            String licensePlate,
                            String vehicleModel,
                            String vehicleColor,
                            String daysVehicleReportedAsParked) {
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
        this.SSA = SSA;
        this.licensePlate = licensePlate;
        this.vehicleModel = vehicleModel;
        this.vehicleColor = vehicleColor;
        this.daysVehicleReportedAsParked = daysVehicleReportedAsParked;
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


    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }


    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }


    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }


    public String getDaysVehicleReportedAsParked() {
        return daysVehicleReportedAsParked;
    }

    public void setDaysVehicleReportedAsParked(String daysVehicleReportedAsParked) {
        this.daysVehicleReportedAsParked = daysVehicleReportedAsParked;
    }

}
