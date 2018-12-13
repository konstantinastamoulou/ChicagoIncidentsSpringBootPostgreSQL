package gr.di.uoa.chicagoincidents.model.datatypes;

public class RequestTypeZipCode {

    private String serviceRequestType;

    private String zipCode;


    public RequestTypeZipCode() {
    }

    public RequestTypeZipCode(String serviceRequestType, String zipCode) {
        this.serviceRequestType = serviceRequestType;
        this.zipCode = zipCode;
    }

    public String getRequestType() {
        return serviceRequestType;
    }

    public void setServiceRequestType(String serviceRequestType) {
        this.serviceRequestType = serviceRequestType;
    }


    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
