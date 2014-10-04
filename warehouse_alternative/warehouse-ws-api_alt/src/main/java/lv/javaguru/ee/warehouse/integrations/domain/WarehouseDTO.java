package lv.javaguru.ee.warehouse.integrations.domain;

/**
 *
 * @author dell
 */
public class WarehouseDTO {
    
    private String title;
        
    private String description;
          
    private String country;
    
    private String city;
        
    private String postIndex;
        
    private String street;
        
    private String house;

    public WarehouseDTO() {
    }

    public WarehouseDTO(String title, String description, String country, String city, String postIndex, String street, String house) {
        this.title = title;
        this.description = description;
        this.country = country;
        this.city = city;
        this.postIndex = postIndex;
        this.street = street;
        this.house = house;
    }

    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
