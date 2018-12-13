package gr.di.uoa.chicagoincidents.model.datatypes;

public class RodentBaitingWithRats {

    private String requestNumber;

    private Integer numOfPremisesWithRats;

    public RodentBaitingWithRats() {
    }

    public RodentBaitingWithRats(String requestNumber, Integer numOfPremisesWithRats) {
        this.requestNumber = requestNumber;
        this.numOfPremisesWithRats = numOfPremisesWithRats;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }


    public Integer getNumOfPremisesWithRats() {
        return numOfPremisesWithRats;
    }

    public void setNumOfPremisesWithRats(Integer requestType) {
        this.numOfPremisesWithRats = numOfPremisesWithRats;
    }

}
