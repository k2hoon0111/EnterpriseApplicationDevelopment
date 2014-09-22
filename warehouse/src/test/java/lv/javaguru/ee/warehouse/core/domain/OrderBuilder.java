package lv.javaguru.ee.warehouse.core.domain;

/**
 * Created by Yuri D. on 2014.09.23..
 */
public class OrderBuilder {

    private Long id;
    private String productId;
    private Long clientId;
    private int itemsQuantity;

    private OrderBuilder() {}

    public static OrderBuilder createOrder() {
        return new OrderBuilder();
    }


    public Order build() {
        Order order = new Order();
        order.setId(id);
        order.setClientId(clientId);
        order.setProductId(productId);
        order.setItemsQuantity(itemsQuantity);
        return order;
    }

    public OrderBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public OrderBuilder withProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public OrderBuilder withClientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public OrderBuilder withItemsQuantity(int itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
        return this;
    }

}
