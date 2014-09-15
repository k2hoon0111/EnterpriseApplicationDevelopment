//package lv.javaguru.ee.bookshop.core.services;
//
//import lv.javaguru.ee.bookshop.core.DomainCommandService;
//import lv.javaguru.ee.bookshop.core.commands.CreateOrderCommand;
//import lv.javaguru.ee.bookshop.core.commands.CreateOrderCommandResult;
//import lv.javaguru.ee.bookshop.core.database.OrderDAO;
//import lv.javaguru.ee.bookshop.core.database.CategoryDAO;
//import lv.javaguru.ee.bookshop.core.domain.Order;
//import lv.javaguru.ee.bookshop.core.domain.Category;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import static com.google.common.base.Preconditions.checkNotNull;
//
//@Component
//public class CreateOrderCommandHandler
//        implements DomainCommandService<CreateOrderCommand, CreateOrderCommandResult> {
//
//    @Autowired
//    private OrderDAO orderDAO;
//
//    @Autowired
//    private CategoryDAO categoryDAO;
//
//
//    @Override
//    public CreateOrderCommandResult execute(CreateOrderCommand command) {
//        validateCommand(command);
//
//        Category category = categoryDAO.getById(command.getCategoryId());
//        Order book = createOrderEntityFromCommand(command, category);
//        bookDAO.create(book);
//
//        return new CreateOrderCommandResult(book);
//    }
//
//    private Order createOrderEntityFromCommand(CreateOrderCommand command, Category category) {
//        Order book = new Order();
//        book.setTitle(command.getTitle());
//        book.setDescription(command.getDescription());
//        book.setPrice(command.getPrice());
//        book.setYear(command.getYear());
//        book.setAuthor(command.getAuthor());
//        book.setIsbn(command.getIsbn());
//        book.setCategory(category);
//        return book;
//    }
//
//    private void validateCommand(CreateOrderCommand command) {
//
//        checkNotNull(command, "CreateOrderCommand must not be null");
//        checkNotNull(command.getTitle(), "Title must not be null");
//        checkNotNull(command.getDescription(), "Description must not be null");
//        checkNotNull(command.getPrice(), "Price must not be null");
//        checkNotNull(command.getYear(), "Year must not be null");
//        checkNotNull(command.getAuthor(), "Author name must not be null");
//        checkNotNull(command.getIsbn(), "Isbn name must not be null");
//
//    }
//
//    @Override
//    public Class getCommandType() {
//        return CreateOrderCommand.class;
//    }
//
//}
