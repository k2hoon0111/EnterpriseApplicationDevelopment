package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.GetOrderCommand;
import lv.javaguru.ee.bookshop.core.commands.GetOrderResult;
import lv.javaguru.ee.bookshop.core.database.jpa.JPACRUDOperationDAO;
import lv.javaguru.ee.bookshop.core.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by MumboJumbo on 20/09/14.
 */

@Component
public class GetOrderCommandHandler
        implements DomainCommandHandler<GetOrderCommand, GetOrderResult> {

    @Autowired
    private JPACRUDOperationDAO jpacrudOperationDAO;


    @Override
    public GetOrderResult execute(GetOrderCommand command) {
        validateCommand(command);
        Order order = jpacrudOperationDAO.getById(Order.class, command.getOrderId());

        if (order == null || !order.getOrderId().equals(command.getOrderId())) {
            throw new RuntimeException("Order id not valid");
        }

        return new GetOrderResult(order);
    }

    private void validateCommand(GetOrderCommand command) {
        checkNotNull(command, "GetOrderCommand must not be null");
        checkNotNull(command.getOrderId(), "Order id must not be null");
    }

    @Override
    public Class getCommandType() {
        return GetOrderCommand.class;
    }
}
