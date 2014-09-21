package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.domain.Order;

/**
 * Created by MumboJumbo on 21/09/14.
 */

public class DeleteOrderResult implements DomainCommandResult {

    private Order order;

    public DeleteOrderResult(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

}