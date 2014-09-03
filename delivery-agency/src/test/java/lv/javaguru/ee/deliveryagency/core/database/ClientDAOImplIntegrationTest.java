package lv.javaguru.ee.deliveryagency.core.database;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

import lv.javaguru.ee.deliveryagency.core.domain.Client;
import lv.javaguru.ee.deliveryagency.core.domain.Delivery;

public class ClientDAOImplIntegrationTest extends DatabaseIntegrationTest {
	
	@Test
	public void testCreateClient() {
		Delivery delivery = createDefaultDelivery();
		Client client = getDefaultClient(delivery);
		assertThat(client.getClientId(), is(nullValue()));
		saveClient(client);
		assertThat(client.getClientId(), is(notNullValue()));
	}
	
}
