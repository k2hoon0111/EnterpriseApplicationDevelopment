package lv.javaguru.ee.bookshop.core.commands;

/**
 * Created by nikoboro on 2014.09.19..
 */


import java.util.Date;

/**
 * Created by nikoboro on 2014.09.18..
 */

public class UpdateAccountCommand implements DomainCommand<UpdateAccountResult> {

    private Long accountId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String emailAddress;
    private String username;
    private String password;

    private String street;
    private String houseNumber;
    private String boxNumber;
    private String city;
    private String postalCode;
    private String country;

    public UpdateAccountCommand(Long accountId, String firstName, String lastName, Date dateOfBirth, String emailAddress, String username, String password, String street, String houseNumber, String boxNumber, String city, String postalCode, String country) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.street = street;
        this.houseNumber = houseNumber;
        this.boxNumber = boxNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
