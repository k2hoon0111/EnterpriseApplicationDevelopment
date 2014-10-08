package lv.javaguru.ee.deliveryagency.core.services;

import static lv.javaguru.ee.deliveryagency.core.commands.builders.CreateClientCommandBuilder.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import lv.javaguru.ee.deliveryagency.core.services.client.CreateClientCommandHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import lv.javaguru.ee.deliveryagency.core.commands.client.CreateClientCommand;
import lv.javaguru.ee.deliveryagency.core.database.ClientDAO;
import lv.javaguru.ee.deliveryagency.core.database.DeliveryDAO;

@RunWith(MockitoJUnitRunner.class)
public class CreateClientCommandHandlerTest {

	@Mock
	private DeliveryDAO deliveryDAO;

	@Mock
	private ClientDAO clientDAO;

	@InjectMocks
	private CreateClientCommandHandler handler = new CreateClientCommandHandler();
	
	
	@Test
	public void testExecuteShouldThrowExceptionWhenCommandIsNull() {
		executeValidation(null, "CreateClientCommand must not be null");
	}

	@Test
	public void testExecuteShouldThrowExceptionWhenDeliveryIdIsNull() {
		CreateClientCommand command = createCreateClientCommand().withDeliveryId(null).build();
		executeValidation(command, "DeliveryId must not be null");
	}
	
	private void executeValidation(CreateClientCommand command, String errorMessage) {
		try {
			handler.execute(command);
			fail();
		} catch (Throwable e) {
			assertThat(e.getMessage(), is(equalTo(errorMessage)));
		}
	}
	
}