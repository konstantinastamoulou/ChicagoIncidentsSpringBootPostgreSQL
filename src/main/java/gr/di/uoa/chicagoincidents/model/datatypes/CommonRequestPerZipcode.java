package gr.di.uoa.chicagoincidents.model.datatypes;

public class CommonRequestPerZipcode {

    private String requestType;

    private Integer zipCode;

    public CommonRequestPerZipcode() {
    }

    public CommonRequestPerZipcode(String requestType, Integer zipCode) {
        this.requestType = requestType;
        this.zipCode = zipCode;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }


    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

}
