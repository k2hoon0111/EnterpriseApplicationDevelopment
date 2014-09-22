package lv.javaguru.ee.bookshop.core.commands;

import java.util.Date;

/**
 * Created by MumboJumbo on 21/09/14.
 */

public class UpdateOrderCommand implements DomainCommand<UpdateOrderResult> {

    private Long orderId;
    private Long accountId;
    private String shippingStreet;
    private String shippingHouseNumber;
    private String shippingBoxNumber;
    private String shippingCity;
    private String shippingPostalCode;
    private String shippingCountry;
    private String billingStreet;
    private String billingHouseNumber;
    private String billingBoxNumber;
    private String billingCity;
    private String billingoPostalCode;
    private String billingCountry;
    private boolean billingSameAsShipping;
    private Date deliveryDate;
    private Date orderDate;

    public UpdateOrderCommand(Long orderId, Long accountId, String shippingStreet, String shippingHouseNumber, String shippingBoxNumber, String shippingCity, String shippingPostalCode, String shippingCountry, String billingStreet, String billingHouseNumber, String billingBoxNumber, String billingCity, String billingoPostalCode, String billingCountry, boolean billingSameAsShipping, Date deliveryDate, Date orderDate) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.shippingStreet = shippingStreet;
        this.shippingHouseNumber = shippingHouseNumber;
        this.shippingBoxNumber = shippingBoxNumber;
        this.shippingCity = shippingCity;
        this.shippingPostalCode = shippingPostalCode;
        this.shippingCountry = shippingCountry;
        this.billingStreet = billingStreet;
        this.billingHouseNumber = billingHouseNumber;
        this.billingBoxNumber = billingBoxNumber;
        this.billingCity = billingCity;
        this.billingoPostalCode = billingoPostalCode;
        this.billingCountry = billingCountry;
        this.billingSameAsShipping = billingSameAsShipping;
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getShippingStreet() {
        return shippingStreet;
    }

    public void setShippingStreet(String shippingStreet) {
        this.shippingStreet = shippingStreet;
    }

    public String getShippingHouseNumber() {
        return shippingHouseNumber;
    }

    public void setShippingHouseNumber(String shippingHouseNumber) {
        this.shippingHouseNumber = shippingHouseNumber;
    }

    public String getShippingBoxNumber() {
        return shippingBoxNumber;
    }

    public void setShippingBoxNumber(String shippingBoxNumber) {
        this.shippingBoxNumber = shippingBoxNumber;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingPostalCode() {
        return shippingPostalCode;
    }

    public void setShippingPostalCode(String shippingPostalCode) {
        this.shippingPostalCode = shippingPostalCode;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public String getBillingHouseNumber() {
        return billingHouseNumber;
    }

    public void setBillingHouseNumber(String billingHouseNumber) {
        this.billingHouseNumber = billingHouseNumber;
    }

    public String getBillingBoxNumber() {
        return billingBoxNumber;
    }

    public void setBillingBoxNumber(String billingBoxNumber) {
        this.billingBoxNumber = billingBoxNumber;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingoPostalCode() {
        return billingoPostalCode;
    }

    public void setBillingoPostalCode(String billingoPostalCode) {
        this.billingoPostalCode = billingoPostalCode;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public boolean isBillingSameAsShipping() {
        return billingSameAsShipping;
    }

    public void setBillingSameAsShipping(boolean billingSameAsShipping) {
        this.billingSameAsShipping = billingSameAsShipping;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
