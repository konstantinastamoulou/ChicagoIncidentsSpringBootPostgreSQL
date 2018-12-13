package gr.di.uoa.chicagoincidents.model.datatypes;

public class RodentBaitingBaited {

    private String requestNumber;

    private Integer numOfPremisesBaited;

    public RodentBaitingBaited() {
    }

    public RodentBaitingBaited(String requestNumber, Integer numOfPremisesBaited) {
        this.requestNumber = requestNumber;
        this.numOfPremisesBaited = numOfPremisesBaited;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }


    public Integer getNumOfPremisesBaited() {
        return numOfPremisesBaited;
    }

    public void setNumOfPremisesBaited(Integer numOfPremisesBaited) {
        this.numOfPremisesBaited = numOfPremisesBaited;
    }

}
