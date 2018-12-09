package gr.di.uoa.chicagoincidents.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="tree_trims")
public class TreeTrim extends ServiceRequest{

    private String treesLocation;

    public TreeTrim() {

    }

    public TreeTrim(Date creationDate,
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
                    String treesLocation) {
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
        this.treesLocation = treesLocation;

    }

    public String getTreesLocation() {
        return treesLocation;
    }

    public void setTreesLocation(String treesLocation) {
        this.treesLocation = treesLocation;
    }

}
