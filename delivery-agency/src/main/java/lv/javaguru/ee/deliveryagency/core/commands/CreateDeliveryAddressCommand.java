package lv.javaguru.ee.deliveryagency.core.commands;

import lv.javaguru.ee.deliveryagency.core.DomainCommand;

/**
 * Created by Viktor on 27/07/2014.
 */
public class CreateDeliveryAddressCommand implements DomainCommand {

    private String city;
    private String postIndex;
    private String street;
    private String house;
    private String flat;


    public CreateDeliveryAddressCommand(String city,
                                        String postIndex,
                                        String street,
                                        String house,
                                        String flat) {
        this.city = city;
        this.postIndex = postIndex;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public String getCity() {
        return city;
    }

    public String getPostIndex() {
        return postIndex;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getFlat() {
        return flat;
    }
}
