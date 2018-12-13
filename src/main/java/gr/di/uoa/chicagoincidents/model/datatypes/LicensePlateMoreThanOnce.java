package gr.di.uoa.chicagoincidents.model.datatypes;

public class LicensePlateMoreThanOnce {

    private String licensePlate;

    private Integer platesCount;

    public LicensePlateMoreThanOnce() {

    }

    public LicensePlateMoreThanOnce(String licensePlate, Integer platesCount) {
        this.licensePlate = licensePlate;
        this.platesCount = platesCount;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }


    public Integer getPlatesCount() {
        return platesCount;
    }

    public void setPlatesCount(Integer platesCount) {
        this.platesCount = platesCount;
    }
}
