package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.UpdateOrderDetailCommand;
import lv.javaguru.ee.bookshop.core.commands.UpdateOrderDetailResult;
import lv.javaguru.ee.bookshop.core.database.jpa.JPACRUDOperationDAO;
import lv.javaguru.ee.bookshop.core.domain.Book;
import lv.javaguru.ee.bookshop.core.domain.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by MumboJumbo on 21/09/14.
 */
@Component
public class UpdateOrderDetailCommandHandler
        implements DomainCommandHandler<UpdateOrderDetailCommand, UpdateOrderDetailResult> {

    @Autowired
    private JPACRUDOperationDAO jpacrudOperationDAO;

    @Override
    public UpdateOrderDetailResult execute(UpdateOrderDetailCommand command) {
        validateCommand(command);

        Book book = jpacrudOperationDAO.getById(Book.class, command.getBookId());

        OrderDetail orderDetail = selectOrderDetailEntityFromDB(command, book);
        jpacrudOperationDAO.update(orderDetail);

        return new UpdateOrderDetailResult(orderDetail);
    }

    private OrderDetail selectOrderDetailEntityFromDB(UpdateOrderDetailCommand command, Book book) {
        OrderDetail orderDetail = jpacrudOperationDAO.getById(OrderDetail.class, command.getOrderDetailId());
        orderDetail.setOrderId(command.getOrderId());
        orderDetail.setBook(book);
        orderDetail.setQuantity(command.getQuantity());
        return orderDetail;
    }

    private void validateCommand(UpdateOrderDetailCommand command) {
        checkNotNull(command, "UpdateOrderDetailCommand must not be null");
        checkNotNull(command.getOrderDetailId(), "OrderDetail id  must not be null");
        checkNotNull(command.getOrderId(), "Order id must not be null");
        checkNotNull(command.getBookId(), "Book id must not be null");
        checkNotNull(command.getQuantity(), "Quantity must not be null");
    }

    @Override
    public Class getCommandType() {
        return UpdateOrderDetailCommand.class;
    }

}

