package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.CreateBookCommand;
import lv.javaguru.ee.bookshop.core.commands.CreateBookResult;
import lv.javaguru.ee.bookshop.core.database.BookDAO;
import lv.javaguru.ee.bookshop.core.database.CategoryDAO;
import lv.javaguru.ee.bookshop.core.domain.Book;
import lv.javaguru.ee.bookshop.core.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class CreateBookCommandHandler
        implements DomainCommandHandler<CreateBookCommand, CreateBookResult> {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private CategoryDAO categoryDAO;


    @Override
    public CreateBookResult execute(CreateBookCommand command) {
        validateCommand(command);

        Category category = categoryDAO.getById(command.getCategoryId());
        Book book = createBookEntityFromCommand(command, category);
        bookDAO.create(book);

        return new CreateBookResult(book);
    }

    private Book createBookEntityFromCommand(CreateBookCommand command, Category category) {
        Book book = new Book();
        book.setTitle(command.getTitle());
        book.setDescription(command.getDescription());
        book.setPrice(command.getPrice());
        book.setYear(command.getYear());
        book.setAuthor(command.getAuthor());
        book.setIsbn(command.getIsbn());
        book.setCategory(category);
        return book;
    }

    private void validateCommand(CreateBookCommand command) {
        checkNotNull(command, "CreateBookCommand must not be null");
        checkNotNull(command.getTitle(), "Title must not be null");
        checkNotNull(command.getDescription(), "Description must not be null");
        checkNotNull(command.getPrice(), "Price must not be null");
        checkNotNull(command.getYear(), "Year must not be null");
        checkNotNull(command.getAuthor(), "Author name must not be null");
        checkNotNull(command.getIsbn(), "Isbn name must not be null");
        checkNotNull(command.getCategoryId(), "Category Id must not be null");
    }

    @Override
    public Class getCommandType() {
        return CreateBookCommand.class;
    }

}
