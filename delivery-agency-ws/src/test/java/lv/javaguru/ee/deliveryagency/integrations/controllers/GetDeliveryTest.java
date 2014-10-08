package lv.javaguru.ee.deliveryagency.integrations.controllers;

import static junit.framework.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import lv.javaguru.ee.deliveryagency.integrations.RestException;
import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;
import lv.javaguru.ee.deliveryagency.integrations.jetty.EmbeddedJettyTest;

import javax.ws.rs.core.Response;

/**
 * Created by Viktor on 16/09/2014.
 */
public class GetDeliveryTest extends EmbeddedJettyTest {

    @Test
    public void testGetDelivery() {
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        DeliveryDTO createdDeliveryDTO = RestFixture.createDelivery(deliveryDTO);
        assertThat(createdDeliveryDTO.getDeliveryId(), is(notNullValue()));

        DeliveryDTO getDeliveryDTO = RestFixture.getDelivery(createdDeliveryDTO.getDeliveryId());
        assertThat(getDeliveryDTO.getDeliveryId(), is(notNullValue()));
    }

    @Test
    public void testGetDelivery_Failed() {
        try {
            RestFixture.getDelivery(Long.MAX_VALUE);
            fail();
        } catch (RestException e) {
            assertEquals(422, e.getHttpStatus());
        }
    }

}
