package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.domain.OrderDetail;

/**
 * Created by MumboJumbo on 21/09/14.
 */
public class CreateOrderDetailResult implements DomainCommandResult {

    private OrderDetail orderDetail;

    public CreateOrderDetailResult(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

}