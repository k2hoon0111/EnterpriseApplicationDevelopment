package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.DeleteOrderCommand;
import lv.javaguru.ee.bookshop.core.commands.DeleteOrderResult;
import lv.javaguru.ee.bookshop.core.database.OrderDAO;
import lv.javaguru.ee.bookshop.core.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by MumboJumbo on 21/09/14.
 */

@Component
public class DeleteOrderCommandHandler
        implements DomainCommandHandler<DeleteOrderCommand, DeleteOrderResult> {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public DeleteOrderResult execute(DeleteOrderCommand command) {
        validateCommand(command);
        Order order = selectOrderEntityFromDB(command);
        orderDAO.delete(order);

        return new DeleteOrderResult(order);
    }

    private Order selectOrderEntityFromDB(DeleteOrderCommand command) {
        Order order = orderDAO.getById(command.getOrderId());
        return order;
    }

    private void validateCommand(DeleteOrderCommand command) {
        checkNotNull(command, "DeleteOrderCommand must not be null");
        checkNotNull(command.getOrderId(), "Order id  must not be null");
    }

    @Override
    public Class getCommandType() {
        return DeleteOrderCommand.class;
    }

}

