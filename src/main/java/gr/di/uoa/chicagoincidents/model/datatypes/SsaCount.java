package gr.di.uoa.chicagoincidents.model.datatypes;

public class SsaCount {

    private String ssa;

    private Integer count;


    public SsaCount() {
    }

    public SsaCount(String ssa, Integer count) {
        this.ssa = ssa;
        this.count = count;
    }

    public String getSsa() {
        return ssa;
    }

    public void setSsa(String ssa) {
        this.ssa = ssa;
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
