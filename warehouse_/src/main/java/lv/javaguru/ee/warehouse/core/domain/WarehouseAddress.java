package lv.javaguru.ee.warehouse.core.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author dell
 */
@Embeddable
public class WarehouseAddress {
    
    private String country;
    
    private String city;
        
    private String postIndex;
        
    private String street;
        
    private String house;

    public WarehouseAddress() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(String postIndex) {
        this.postIndex = postIndex;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
            
}
