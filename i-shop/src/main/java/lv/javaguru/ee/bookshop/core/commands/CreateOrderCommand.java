package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.DomainCommand;
import lv.javaguru.ee.bookshop.core.domain.Account;
import lv.javaguru.ee.bookshop.core.domain.Address;
import lv.javaguru.ee.bookshop.core.domain.OrderDetail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateOrderCommand implements DomainCommand {
    private Long orderId;
    private Address shippingAddress;
    private Address billingAddress;
    private Account account;
    private boolean billingSameAsShipping = true;
    private Date orderDate;
    private Date deliveryDate;
    private BigDecimal totalOrderPrice = null;
    private final List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

    public CreateOrderCommand(Long orderId,
                              Address shippingAddress,
                              Address billingAddress,
                              Account account,
                              boolean billingSameAsShipping,
                              Date orderDate,
                              Date deliveryDate,
                              BigDecimal totalOrderPrice) {
        this.orderId = orderId;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.account = account;
        this.billingSameAsShipping = billingSameAsShipping;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.totalOrderPrice = totalOrderPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public Account getAccount() {
        return account;
    }

    public boolean isBillingSameAsShipping() {
        return billingSameAsShipping;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public BigDecimal getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }
}
