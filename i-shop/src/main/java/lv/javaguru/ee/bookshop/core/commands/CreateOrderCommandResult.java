package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.DomainCommandResult;
import lv.javaguru.ee.bookshop.core.domain.Order;

public class CreateOrderCommandResult implements DomainCommandResult {

    private Order order;

    public CreateOrderCommandResult(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
