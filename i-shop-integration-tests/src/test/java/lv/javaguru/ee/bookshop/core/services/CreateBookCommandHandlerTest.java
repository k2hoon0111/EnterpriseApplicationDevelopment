package lv.javaguru.ee.bookshop.core.services;


import lv.javaguru.ee.bookshop.core.commands.CreateBookCommand;
import lv.javaguru.ee.bookshop.core.commands.CreateBookCommandResult;
import lv.javaguru.ee.bookshop.core.database.hibernate.DatabaseHibernateTest;
import lv.javaguru.ee.bookshop.core.domain.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateBookCommandHandlerTest extends DatabaseHibernateTest {

    @Autowired
    private DomainCommandHandlerExecutor serviceExecutor;

    @Test
    public void testCommand() {
        Book book = getDefaultBook();
        CreateBookCommand command = new CreateBookCommand(
                book.getTitle(),
                book.getDescription(),
                book.getPrice(),
                book.getYear(),
                book.getAuthor(),
                book.getIsbn(),
                book.getCategory().getCategoryId()
        );
        CreateBookCommandResult commandResult = serviceExecutor.execute(command);
        assertThat(commandResult, is(notNullValue()));
        assertThat(commandResult.getBook(), is(notNullValue()));
        assertThat(commandResult.getBook().getBookId(), is(notNullValue()));
    }

}
