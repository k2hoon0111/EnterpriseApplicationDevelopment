package lv.javaguru.ee.deliveryagency.integrations.controllers;

import lv.javaguru.ee.deliveryagency.integrations.domain.ClientDTO;
import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;
import lv.javaguru.ee.deliveryagency.integrations.jetty.EmbeddedJettyTest;
import org.junit.Before;
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
public class GetClientTest extends EmbeddedJettyTest {

    private Long deliveryId;


    @Before
    public void initTestData() {
        DeliveryDTO createDeliveryDTO = RestFixture.createDelivery();
        assertThat(createDeliveryDTO.getDeliveryId(), is(notNullValue()));
        this.deliveryId = createDeliveryDTO.getDeliveryId();
    }

    @Test
    public void testGetClient() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName("A");
        clientDTO.setLastName("B");
        clientDTO.setEmail("email");
        clientDTO.setPhone("1111");
        clientDTO.setSpecialNotes("notes");

        ResponseEntity<ClientDTO> createClientResponse
                = RestFixture.createClient(deliveryId, clientDTO);
        ClientDTO createClientDTO = createClientResponse.getBody();

        Long clientId = createClientDTO.getClientId();

        ResponseEntity<ClientDTO> getClientResponse
                = RestFixture.getClient(deliveryId, clientId);
        ClientDTO getClientDTO = getClientResponse.getBody();
        assertThat(getClientDTO.getClientId(), is(clientId));
        assertThat(getClientDTO.getFirstName(), is("A"));
        assertThat(getClientDTO.getLastName(), is("B"));
    }

    @Test
    public void testGetClientWithWrongId() {
        try {
            RestFixture.getClient(deliveryId, Long.MAX_VALUE);
        } catch (HttpClientErrorException e) {
            assertEquals("Client id not valid", e.getResponseBodyAsString());
            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
        }
    }

}
