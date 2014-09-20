package lv.javaguru.ee.bookshop.core.commands;

import java.util.Date;

public class CreateOrderCommand implements DomainCommand<CreateOrderResult> {

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
//    private BigDecimal totalOrderPrice = null;
//    private final List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();


    public CreateOrderCommand(Long accountId, String shippingStreet, String shippingHouseNumber, String shippingBoxNumber, String shippingCity, String shippingPostalCode, String shippingCountry, String billingStreet, String billingHouseNumber, String billingBoxNumber, String billingCity, String billingoPostalCode, String billingCountry, boolean billingSameAsShipping, Date deliveryDate, Date orderDate) {

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

    public String getShippingStreet() {
        return shippingStreet;
    }

    public String getShippingHouseNumber() {
        return shippingHouseNumber;
    }

    public String getShippingBoxNumber() {
        return shippingBoxNumber;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public String getShippingPostalCode() {
        return shippingPostalCode;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public String getBillingStreet() {
        return billingStreet;
    }

    public String getBillingHouseNumber() {
        return billingHouseNumber;
    }

    public String getBillingBoxNumber() {
        return billingBoxNumber;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public String getBillingoPostalCode() {
        return billingoPostalCode;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public boolean isBillingSameAsShipping() {
        return billingSameAsShipping;
    }

    public Long getAccountId() {
        return accountId;

    }
}
