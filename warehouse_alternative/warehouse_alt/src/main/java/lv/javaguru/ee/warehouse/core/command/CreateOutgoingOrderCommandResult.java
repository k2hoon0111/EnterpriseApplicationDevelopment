package lv.javaguru.ee.warehouse.core.command;

import lv.javaguru.ee.warehouse.core.domain.Order;

/**
 *
 * @author dell
 */
public class CreateOutgoingOrderCommandResult implements DomainCommandResult<Order> {
    
    private final Order order;

    public CreateOutgoingOrderCommandResult(Order order) {
        this.order = order;
    }

    @Override
    public Order getResult() {
        return order;
    }
            
}
