package lv.javaguru.ee.warehouse.core.services;

import static com.google.common.base.Preconditions.*;
import static java.util.Objects.requireNonNull;
import lv.javaguru.ee.warehouse.core.command.IncomingOrderCommand;
import lv.javaguru.ee.warehouse.core.command.IncomingOrderCommandResult;
import lv.javaguru.ee.warehouse.core.database.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author dell
 */
public class IncomingOrderCommandHandler implements 
        DomainCommandHandler<IncomingOrderCommand, IncomingOrderCommandResult>{

    @Autowired
    private OrderDAO orderDAO;
    
    @Override
    public IncomingOrderCommandResult execute(IncomingOrderCommand command) {
        return null;
    }

    @Override
    public Class<IncomingOrderCommand> getCommandType() {
        return IncomingOrderCommand.class;
    }

    @Override
    public void validate(IncomingOrderCommand command) {
        requireNonNull(command, "IncomingOrder can not be empty");
        requireNonNull(command.getProduct(), "IncomingOrder product can not be empty");
        requireNonNull(command.getProduct().getId(), "IncomingOrder product id can not be empty");
        requireNonNull(command.getWarehouse(), "IncomingOrder warehouse can not be empty");
        requireNonNull(command.getWarehouse().getId(), "IncomingOrder warehouse id can not be empty");
        requireNonNull(command.getAmount(), "IncomingOrder amount can not be empty");
        checkArgument(command.getAmount() == 0, "IncomingOrder amount can not be 0");        
        requireNonNull(command.getQuantity(), "IncomingOrder quantity can not be empty");
        checkArgument(command.getAmount() == 0, "IncomingOrder quantity can not be 0");
    }
    
    
    
}
