package lv.javaguru.ee.deliveryagency.integrations.controllers;

import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;
import lv.javaguru.ee.deliveryagency.integrations.jetty.EmbeddedJettyTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Viktor on 16/09/2014.
 */
public class GetDeliveryTest extends EmbeddedJettyTest {

    @Test
    public void testGetDelivery() {
        DeliveryDTO deliveryDTO = new DeliveryDTO();

        ResponseEntity<DeliveryDTO> createDeliveryResponse
                = RestFixture.createDelivery(deliveryDTO);

        DeliveryDTO createDeliveryDTO = createDeliveryResponse.getBody();
        assertThat(createDeliveryDTO.getDeliveryId(), is(notNullValue()));

        ResponseEntity<DeliveryDTO> getDeliveryResponse
                = RestFixture.getDelivery(createDeliveryDTO.getDeliveryId());
        DeliveryDTO getDeliveryDTO = getDeliveryResponse.getBody();
        assertThat(getDeliveryDTO.getDeliveryId(), is(notNullValue()));
    }

    @Test
    public void testGetDelivery_Failed() {
        try {
            RestFixture.getDelivery(Long.MAX_VALUE);
        } catch (HttpClientErrorException e) {
            //assertEquals("Loan not found", e.getResponseBodyAsString());
            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
        }
    }

}
