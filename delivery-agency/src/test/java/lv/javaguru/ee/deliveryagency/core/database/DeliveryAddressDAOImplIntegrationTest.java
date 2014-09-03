package lv.javaguru.ee.deliveryagency.core.database;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
import lv.javaguru.ee.deliveryagency.core.domain.DeliveryAddress;

public class DeliveryAddressDAOImplIntegrationTest extends DatabaseIntegrationTest {

	@Test
	public void testCreateDeliveryAddress() {
		Delivery delivery = createDefaultDelivery();
		DeliveryAddress deliveryAddress = getDefaultDeliveryAddress(delivery);
		assertThat(deliveryAddress.getDeliveryAddressId(), is(nullValue()));
		saveDeliveryAddress(deliveryAddress);
		assertThat(deliveryAddress.getDeliveryAddressId(), is(notNullValue()));
	}

}
