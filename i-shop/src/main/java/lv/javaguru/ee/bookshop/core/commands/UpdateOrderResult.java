package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.domain.Order;

/**
 * Created by MumboJumbo on 21/09/14.
 */
public class UpdateOrderResult implements DomainCommandResult {

    private Order order;

    public UpdateOrderResult(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}