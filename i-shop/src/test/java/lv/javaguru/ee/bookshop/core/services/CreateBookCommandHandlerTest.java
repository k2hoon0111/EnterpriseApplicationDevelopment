//package lv.javaguru.ee.bookshop.core.services;
//
//import lv.javaguru.ee.bookshop.core.commands.CreateBookCommand;
//import lv.javaguru.ee.bookshop.core.commands.CreateClientCommand;
//import lv.javaguru.ee.bookshop.core.database.BookDAO;
//import lv.javaguru.ee.bookshop.core.database.ClientDAO;
//import lv.javaguru.ee.bookshop.core.database.DeliveryDAO;
//import lv.javaguru.ee.bookshop.core.domain.Category;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import static lv.javaguru.ee.bookshop.core.commands.builders.CreateClientCommandBuilder.*;
//import static lv.javaguru.ee.bookshop.core.commands.builders.CreateClientCommandBuilder.createCreateClientCommand;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.fail;
//
//@RunWith(MockitoJUnitRunner.class)
//public class CreateBookCommandHandlerTest {
//
//	@Mock
//	private BookDAO bookDAO;
//
//	@Mock
//	private CategoryDAO categoryDAO;
//
//	@InjectMocks
//	private CreateBookCommandHandler handler = new CreateBookCommandHandler();
//
//
//	@Test
//	public void testExecuteShouldThrowExceptionWhenCommandIsNull() {
//		executeValidation(null, "CreateBookCommand must not be null");
//	}
//
//	@Test
//	public void testExecuteShouldThrowExceptionWhenCategoryIdIsNull() {
//		CreateBookCommand command =     new    CreateBookCommand();
//
//
//                createBookCommand().withDeliveryId(null).build();
//
//
//
//
//		executeValidation(command, "Category must not be null");
//	}
//
//	private void executeValidation(CreateClientCommand command, String errorMessage) {
//		try {
//			handler.execute(command);
//			fail();
//		} catch (Throwable e) {
//			assertThat(e.getMessage(), is(equalTo(errorMessage)));
//		}
//	}
//
//}