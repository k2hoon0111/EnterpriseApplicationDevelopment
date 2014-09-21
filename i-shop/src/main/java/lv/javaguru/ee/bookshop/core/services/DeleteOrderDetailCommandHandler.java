package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.DeleteOrderDetailCommand;
import lv.javaguru.ee.bookshop.core.commands.DeleteOrderDetailResult;
import lv.javaguru.ee.bookshop.core.database.OrderDetailDAO;
import lv.javaguru.ee.bookshop.core.domain.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by MumboJumbo on 21/09/14.
 */

@Component
public class DeleteOrderDetailCommandHandler
        implements DomainCommandHandler<DeleteOrderDetailCommand, DeleteOrderDetailResult> {

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Override
    public DeleteOrderDetailResult execute(DeleteOrderDetailCommand command) {
        validateCommand(command);
        OrderDetail orderDetail = selectOrderDetailEntityFromDB(command);
        orderDetailDAO.delete(orderDetail);

        return new DeleteOrderDetailResult(orderDetail);
    }

    private OrderDetail selectOrderDetailEntityFromDB(DeleteOrderDetailCommand command) {
        OrderDetail orderDetail = orderDetailDAO.getById(command.getOrderDetailId());
        return orderDetail;
    }

    private void validateCommand(DeleteOrderDetailCommand command) {
        checkNotNull(command, "DeleteOrderDetailCommand must not be null");
        checkNotNull(command.getOrderDetailId(), "OrderDetail id  must not be null");
    }

    @Override
    public Class getCommandType() {
        return DeleteOrderDetailCommand.class;
    }

}
