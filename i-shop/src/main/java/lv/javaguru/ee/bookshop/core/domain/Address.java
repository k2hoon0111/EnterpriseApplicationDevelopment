package lv.javaguru.ee.bookshop.core.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Embeddable;
import java.io.Serializable;


/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 10:09
 * To change this template use File | Settings | File Templates.
 */

@Embeddable
//@Table(name = "addresses")
public class Address implements Serializable {

    @NotEmpty
    private String street;
    private String houseNumber;
    private String boxNumber;

    @NotEmpty
    private String city;
    private String postalCode;

    @NotEmpty
    private String country;

    public Address() {
        super();
    }

    public Address(Address source) {
        super();
        this.street = source.street;
        this.houseNumber = source.houseNumber;
        this.boxNumber = source.boxNumber;
        this.city = source.city;
        this.postalCode = source.postalCode;
        this.country = source.country;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getBoxNumber() {
        return this.boxNumber;

    }

    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}