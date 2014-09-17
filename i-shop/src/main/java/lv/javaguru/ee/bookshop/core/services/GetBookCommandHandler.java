package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.GetBookCommand;
import lv.javaguru.ee.bookshop.core.commands.GetBookResult;
import lv.javaguru.ee.bookshop.core.database.BookDAO;
import lv.javaguru.ee.bookshop.core.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;


@Component
public class GetBookCommandHandler
        implements DomainCommandHandler<GetBookCommand, GetBookResult> {

    @Autowired
    private BookDAO bookDAO;


    @Override
    public GetBookResult execute(GetBookCommand command) {
        validateCommand(command);
        Book book = bookDAO.getById(command.getBookId());

        if (book == null || !book.getBookId().equals(command.getBookId())) {
            throw new RuntimeException("Book id not valid");
        }

        return new GetBookResult(book);
    }

    private void validateCommand(GetBookCommand command) {
        checkNotNull(command, "GetBookCommand must not be null");
        checkNotNull(command.getBookId(), "Book id must not be null");
//        checkNotNull(command.getCategoryId(), "Category must not be null");
    }

    @Override
    public Class getCommandType() {
        return GetBookCommand.class;
    }
}
