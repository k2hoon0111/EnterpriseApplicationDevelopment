package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.GetOrderDetailCommand;
import lv.javaguru.ee.bookshop.core.commands.GetOrderDetailResult;
import lv.javaguru.ee.bookshop.core.database.jpa.JPACRUDOperationDAO;
import lv.javaguru.ee.bookshop.core.domain.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by MumboJumbo on 21/09/14.
 */
@Component
public class GetOrderDetailCommandHandler
        implements DomainCommandHandler<GetOrderDetailCommand, GetOrderDetailResult> {

    @Autowired
    private JPACRUDOperationDAO jpacrudOperationDAO;


    @Override
    public GetOrderDetailResult execute(GetOrderDetailCommand command) {
        validateCommand(command);
        OrderDetail orderDetail = jpacrudOperationDAO.getById(OrderDetail.class, command.getOrderDetailId());

        if (orderDetail == null || !orderDetail.getOrderDetailId().equals(command.getOrderDetailId())) {
            throw new RuntimeException("OrderDetail id not valid");
        }

        return new GetOrderDetailResult(orderDetail);
    }

    private void validateCommand(GetOrderDetailCommand command) {
        checkNotNull(command, "GetOrderDetailCommand must not be null");
        checkNotNull(command.getOrderDetailId(), "OrderDetail id must not be null");
    }

    @Override
    public Class getCommandType() {
        return GetOrderDetailCommand.class;
    }
}