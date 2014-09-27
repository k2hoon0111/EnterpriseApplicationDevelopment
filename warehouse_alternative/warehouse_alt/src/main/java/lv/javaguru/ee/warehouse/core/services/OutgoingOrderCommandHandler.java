package lv.javaguru.ee.warehouse.core.services;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;
import lv.javaguru.ee.warehouse.core.command.OutgoingOrderCommand;
import lv.javaguru.ee.warehouse.core.command.OutgoingOrderCommandResult;
import lv.javaguru.ee.warehouse.core.database.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dell
 */
public class OutgoingOrderCommandHandler implements 
        DomainCommandHandler<OutgoingOrderCommand, OutgoingOrderCommandResult>{

    @Autowired
    private OrderDAO orderDAO;
    
    @Override
    public OutgoingOrderCommandResult execute(OutgoingOrderCommand command) {
        return null;
    }

    @Override
    public Class<OutgoingOrderCommand> getCommandType() {
        return OutgoingOrderCommand.class;
    }

    @Override
    public void validate(OutgoingOrderCommand command) {
        requireNonNull(command, "OutgoingOrder can not be empty");
        requireNonNull(command.getProduct(), "OutgoingOrder product can not be empty");
        requireNonNull(command.getProduct().getId(), "OutgoingOrder product id can not be empty");
        requireNonNull(command.getWarehouse(), "OutgoingOrder warehouse can not be empty");
        requireNonNull(command.getWarehouse().getId(), "OutgoingOrder warehouse id can not be empty");
        requireNonNull(command.getAmount(), "OutgoingOrder amount can not be empty");
        checkArgument(command.getAmount() == 0, "OutgoingOrder amount can not be 0");        
        requireNonNull(command.getQuantity(), "OutgoingOrder quantity can not be empty");
        checkArgument(command.getAmount() == 0, "OutgoingOrder quantity can not be 0");
    }
    
}
