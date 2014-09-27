package lv.javaguru.ee.warehouse.core.command;

import lv.javaguru.ee.warehouse.core.domain.Order;

/**
 *
 * @author dell
 */
public class IncomingOrderCommandResult implements DomainCommandResult {
    
    private final Order order;

    public IncomingOrderCommandResult(Order order) {
        this.order = order;
    }
            
}
