package lv.javaguru.ee.deliveryagency.core.database;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

import lv.javaguru.ee.deliveryagency.core.domain.Delivery;

/**
 * Created by Viktor on 03/08/2014.
 */
public class DeliveryDAOImplIntegrationTest extends DatabaseIntegrationTest {

    @Test
    public void testCreate() {
	    Delivery delivery = new Delivery();
	    assertThat(delivery.getDeliveryId(), is(nullValue()));
	    saveDelivery(delivery);
	    assertThat(delivery.getDeliveryId(), is(notNullValue()));
    }

}
