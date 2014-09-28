package lv.javaguru.ee.warehouse.core.command;

import lv.javaguru.ee.warehouse.core.domain.Order;

/**
 *
 * @author dell
 */
public class CreateIncomingOrderCommandResult implements DomainCommandResult {
    
    private final Order order;

    public CreateIncomingOrderCommandResult(Order order) {
        this.order = order;
    }
            
}
