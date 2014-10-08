package lv.javaguru.ee.deliveryagency.integrations.controllers;

import lv.javaguru.ee.deliveryagency.integrations.RestException;
import lv.javaguru.ee.deliveryagency.integrations.domain.ClientDTO;
import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;
import lv.javaguru.ee.deliveryagency.integrations.jetty.EmbeddedJettyTest;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static lv.javaguru.ee.deliveryagency.integrations.domain.builders.ClientDTOBuilder.createClientDTO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Viktor on 05/10/2014.
 */
public class DeleteClientTest extends EmbeddedJettyTest {

    private Long deliveryId;
    private Long clientId;


    @Before
    public void initTestData() {
        DeliveryDTO createDeliveryDTO = RestFixture.createDelivery();
        assertThat(createDeliveryDTO.getDeliveryId(), is(notNullValue()));
        this.deliveryId = createDeliveryDTO.getDeliveryId();

        ClientDTO clientDTO = createClientDTO()
                .withFirstName("A")
                .withLastName("B")
                .withEmail("email")
                .withPhone("1111")
                .withSpecialNotes("notes").build();

        ClientDTO createClientDTO = RestFixture.createClient(deliveryId, clientDTO);
        this.clientId = createClientDTO.getClientId();
    }

    @Test
    public void testDeleteClient() {
        ClientDTO clientDTO = RestFixture.deleteClient(deliveryId, clientId);
        assertThat(clientDTO.getClientId(), is(clientId));
        assertThat(clientDTO.getFirstName(), is("A"));
        assertThat(clientDTO.getLastName(), is("B"));

        try {
            RestFixture.getClient(deliveryId, clientId);
            fail();
        } catch (RestException e) {
            assertEquals(422, e.getHttpStatus());
        }
    }

}
