package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.UpdateBookCommand;
import lv.javaguru.ee.bookshop.core.commands.UpdateBookResult;
import lv.javaguru.ee.bookshop.core.database.BookDAO;
import lv.javaguru.ee.bookshop.core.database.CategoryDAO;
import lv.javaguru.ee.bookshop.core.domain.Book;
import lv.javaguru.ee.bookshop.core.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class UpdateBookCommandHandler
        implements DomainCommandHandler<UpdateBookCommand, UpdateBookResult> {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private CategoryDAO categoryDAO;


    @Override
    public UpdateBookResult execute(UpdateBookCommand command) {
        validateCommand(command);

        Category category = categoryDAO.getById(command.getCategoryId());
        Book book = selectBookEntityFromDB(command, category);
        bookDAO.update(book);

        return new UpdateBookResult(book);
    }

    private Book selectBookEntityFromDB(UpdateBookCommand command, Category category) {
        Book book = bookDAO.getById(command.getBookId());
        book.setTitle(command.getTitle());
        book.setDescription(command.getDescription());
        book.setPrice(command.getPrice());
        book.setYear(command.getYear());
        book.setAuthor(command.getAuthor());
        book.setIsbn(command.getIsbn());
        book.setCategory(category);
        return book;
    }

    private void validateCommand(UpdateBookCommand command) {
        checkNotNull(command, "UpdateBookCommand must not be null");
        checkNotNull(command.getBookId(), "Book id  must not be null");
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
        return UpdateBookCommand.class;
    }

}
