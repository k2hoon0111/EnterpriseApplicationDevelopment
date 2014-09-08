package lv.javaguru.ee.warehouse.core.domain;

/**
 * Created by Yuri D. on 2014.09.08..
 */
public class Order {
    Long id;
    Long productId;
    Long clientId;           // product ordered for user
    int itemsQuantity;      // how many items to order
}
