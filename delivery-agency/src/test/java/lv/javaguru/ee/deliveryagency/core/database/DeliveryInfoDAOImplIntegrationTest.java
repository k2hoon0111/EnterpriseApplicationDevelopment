package lv.javaguru.ee.deliveryagency.core.database;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import org.junit.Test;

import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
import lv.javaguru.ee.deliveryagency.core.domain.DeliveryInfo;

public class DeliveryInfoDAOImplIntegrationTest extends DatabaseIntegrationTest {

	@Test
	public void testCreate() {
		Delivery delivery = createDefaultDelivery();				
		DeliveryInfo deliveryInfo = getDefaultDeliveryInfo();
		assertThat(deliveryInfo.getDeliveryInfoId(), is(nullValue()));
		deliveryInfo.setDelivery(delivery);
		saveDeliveryInfo(deliveryInfo);
		assertThat(deliveryInfo.getDeliveryInfoId(), is(notNullValue()));
	}

}
