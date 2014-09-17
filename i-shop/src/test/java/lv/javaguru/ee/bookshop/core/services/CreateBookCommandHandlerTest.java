package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.CreateBookCommand;
import lv.javaguru.ee.bookshop.core.database.BookDAO;
import lv.javaguru.ee.bookshop.core.database.CategoryDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class CreateBookCommandHandlerTest {

    @Mock
    private BookDAO bookDAO;

    @Mock
    private CategoryDAO categoryDAO;

    @InjectMocks
    private CreateBookCommandHandler handler = new CreateBookCommandHandler();


    @Test
    public void testExecuteShouldThrowExceptionWhenCommandIsNull() {
        executeValidation(null, "CreateBookCommand must not be null");
    }

    @Test
    public void testExecuteShouldThrowExceptionWhenCategoryIsNull() {
        CreateBookCommand command;
        command = new CreateBookCommand(
                "Effective Java",
                "Brings together seventy-eight indispensable programmer's rules of thumb.",
                new BigDecimal("20.20"),
                2002,
                "Joshua Bloch",
                "9780321356680",
                null
        );
        executeValidation(command, "BookId must not be null");
    }

    private void executeValidation(CreateBookCommand command, String errorMessage) {
        try {
            handler.execute(command);
            fail();
        } catch (Throwable e) {
            assertThat(e.getMessage(), is(equalTo(errorMessage)));
        }
    }

}