package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.domain.Order;

/**
 * Created by MumboJumbo on 21/09/14.
 */
public class UpdateOrderResult implements DomainCommandResult {

    private Order category;

    public UpdateOrderResult(Order category) {
        this.category = category;
    }

    public Order getOrder() {
        return category;
    }
}