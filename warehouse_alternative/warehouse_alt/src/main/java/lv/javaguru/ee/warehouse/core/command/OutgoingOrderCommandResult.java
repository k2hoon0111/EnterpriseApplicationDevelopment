package lv.javaguru.ee.warehouse.core.command;

import lv.javaguru.ee.warehouse.core.domain.Order;

/**
 *
 * @author dell
 */
public class OutgoingOrderCommandResult implements DomainCommandResult {
    
    private final Order order;

    public OutgoingOrderCommandResult(Order order) {
        this.order = order;
    }
            
}
