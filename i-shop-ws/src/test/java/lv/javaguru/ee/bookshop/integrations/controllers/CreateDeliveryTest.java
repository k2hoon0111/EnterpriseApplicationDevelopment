package lv.javaguru.ee.deliveryagency.integrations.controllers;

import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;
import lv.javaguru.ee.deliveryagency.integrations.jetty.EmbeddedJettyTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Viktor on 15/09/2014.
 */
public class CreateDeliveryTest extends EmbeddedJettyTest {

    @Test
    public void testCreateDelivery() {
        DeliveryDTO deliveryDTO = new DeliveryDTO();

        ResponseEntity<DeliveryDTO> createDeliveryResponse
                = RestFixture.createDelivery(deliveryDTO);

        DeliveryDTO createDeliveryDTO = createDeliveryResponse.getBody();
        assertThat(createDeliveryDTO.getDeliveryId(), is(notNullValue()));
    }

}
