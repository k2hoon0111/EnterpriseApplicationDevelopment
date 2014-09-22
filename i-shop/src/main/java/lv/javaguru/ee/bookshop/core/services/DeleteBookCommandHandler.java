package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.DeleteBookCommand;
import lv.javaguru.ee.bookshop.core.commands.DeleteBookResult;
import lv.javaguru.ee.bookshop.core.database.jpa.JPACRUDOperationDAO;
import lv.javaguru.ee.bookshop.core.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class DeleteBookCommandHandler
        implements DomainCommandHandler<DeleteBookCommand, DeleteBookResult> {

    @Autowired
    private JPACRUDOperationDAO jpacrudOperationDAO;

    @Override
    public DeleteBookResult execute(DeleteBookCommand command) {
        validateCommand(command);
        Book book = selectBookEntityFromDB(command);
        jpacrudOperationDAO.delete(book);

        return new DeleteBookResult(book);
    }

    private Book selectBookEntityFromDB(DeleteBookCommand command) {
        Book book = jpacrudOperationDAO.getById(Book.class, command.getBookId());
        return book;
    }

    private void validateCommand(DeleteBookCommand command) {
        checkNotNull(command, "UpdateBookCommand must not be null");
        checkNotNull(command.getBookId(), "Book id  must not be null");
    }

    @Override
    public Class getCommandType() {
        return DeleteBookCommand.class;
    }

}