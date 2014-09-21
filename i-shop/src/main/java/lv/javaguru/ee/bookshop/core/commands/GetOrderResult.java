package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.domain.Order;

/**
 * Created by MumboJumbo on 20/09/14.
 */

public class GetOrderResult implements DomainCommandResult {

    private Order order;

    public GetOrderResult(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
