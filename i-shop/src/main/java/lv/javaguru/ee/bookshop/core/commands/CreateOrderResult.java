package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.domain.Order;

public class CreateOrderResult implements DomainCommandResult {

    private Order order;

    public CreateOrderResult(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
