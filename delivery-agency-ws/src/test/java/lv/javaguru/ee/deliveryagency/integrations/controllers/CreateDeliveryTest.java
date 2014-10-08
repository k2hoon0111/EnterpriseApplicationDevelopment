package lv.javaguru.ee.deliveryagency.integrations.controllers;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;
import lv.javaguru.ee.deliveryagency.integrations.jetty.EmbeddedJettyTest;

/**
 * Created by Viktor on 15/09/2014.
 */
public class CreateDeliveryTest extends EmbeddedJettyTest {

    @Test
    public void testCreateDelivery() {
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        DeliveryDTO createdDeliveryDTO = RestFixture.createDelivery(deliveryDTO);
        assertThat(createdDeliveryDTO.getDeliveryId(), is(notNullValue()));
    }

}
