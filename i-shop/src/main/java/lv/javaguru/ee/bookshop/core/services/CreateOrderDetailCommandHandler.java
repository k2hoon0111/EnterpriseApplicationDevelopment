package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.CreateOrderDetailCommand;
import lv.javaguru.ee.bookshop.core.commands.CreateOrderDetailResult;
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
public class CreateOrderDetailCommandHandler
        implements DomainCommandHandler<CreateOrderDetailCommand, CreateOrderDetailResult> {

    @Autowired
    private JPACRUDOperationDAO jpacrudOperationDAO;

    @Override
    public CreateOrderDetailResult execute(CreateOrderDetailCommand command) {
        validateCommand(command);

        Book book = jpacrudOperationDAO.getById(Book.class, command.getBookId());

        OrderDetail orderDetail = createOrderDetailEntityFromCommand(command, book);
        jpacrudOperationDAO.create(orderDetail);

        return new CreateOrderDetailResult(orderDetail);
    }

    private OrderDetail createOrderDetailEntityFromCommand(CreateOrderDetailCommand command, Book book) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(command.getOrderId());
        orderDetail.setBook(book);
        orderDetail.setQuantity(command.getQuantity());

        return orderDetail;
    }

    private void validateCommand(CreateOrderDetailCommand command) {
        checkNotNull(command, "CreateOrderDetailCommand must not be null");
        checkNotNull(command.getOrderId(), "Order id must not be null");
        checkNotNull(command.getBookId(), "Book id must not be null");
        checkNotNull(command.getQuantity(), "Quantity must not be null");
    }

    @Override
    public Class getCommandType() {
        return CreateOrderDetailCommand.class;
    }

}
