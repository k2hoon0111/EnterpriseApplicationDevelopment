package lv.javaguru.ee.deliveryagency.integrations.controllers;

import lv.javaguru.ee.deliveryagency.integrations.domain.ClientDTO;
import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;
import lv.javaguru.ee.deliveryagency.integrations.jetty.EmbeddedJettyTest;
import org.junit.Before;
import org.junit.Test;

import static lv.javaguru.ee.deliveryagency.integrations.domain.builders.ClientDTOBuilder.createClientDTO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Viktor on 06/10/2014.
 */
public class UpdateClientTest extends EmbeddedJettyTest {

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
    public void testUpdateClient() {
        ClientDTO clientDTO = RestFixture.getClient(deliveryId, clientId);
        assertThat(clientDTO.getFirstName(), is("A"));
        clientDTO.setFirstName("WWW");
        RestFixture.updateClient(deliveryId, clientId, clientDTO);
        clientDTO = RestFixture.getClient(deliveryId, clientId);
        assertThat(clientDTO.getFirstName(), is("WWW"));
    }

}
