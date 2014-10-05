package lv.javaguru.ee.warehouse.integrations.domain.builders;

import lv.javaguru.ee.warehouse.integrations.domain.WarehouseDTO;

public class WarehouseDTOBuilder {

    private String title;
    private String description;
    private String country;
    private String city;
    private String postIndex;
    private String street;
    private String house;

    private WarehouseDTOBuilder() {
    }

    public static WarehouseDTOBuilder createWarehouseDTO() {
        return new WarehouseDTOBuilder();
    }

    public WarehouseDTOBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public WarehouseDTOBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public WarehouseDTOBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public WarehouseDTOBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public WarehouseDTOBuilder setPostIndex(String postIndex) {
        this.postIndex = postIndex;
        return this;
    }

    public WarehouseDTOBuilder setStreet(String street) {
        this.street = street;
        return this;
    }

    public WarehouseDTOBuilder setHouse(String house) {
        this.house = house;
        return this;
    }

    public WarehouseDTO build() {
        WarehouseDTO warehouse = new WarehouseDTO();
        warehouse.setTitle(title);
        warehouse.setDescription(description);
        warehouse.setCountry(country);
        warehouse.setCity(city);
        warehouse.setPostIndex(postIndex);
        warehouse.setStreet(street);
        warehouse.setHouse(house);        
        return warehouse;
    }

}
