package gr.di.uoa.chicagoincidents.model.datatypes;

public class RodentBaitingWithGarbage {

    private String requestNumber;

    private Integer numOfPremisesWithGarbage;

    public RodentBaitingWithGarbage() {
    }

    public RodentBaitingWithGarbage(String requestNumber, Integer numOfPremisesWithRats) {
        this.requestNumber = requestNumber;
        this.numOfPremisesWithGarbage = numOfPremisesWithGarbage;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }


    public Integer getNumOfPremisesWithGarbage() {
        return numOfPremisesWithGarbage;
    }

    public void setNumOfPremisesWithGarbabe(Integer numOfPremisesWithGarbage) {
        this.numOfPremisesWithGarbage = numOfPremisesWithGarbage;
    }

}
