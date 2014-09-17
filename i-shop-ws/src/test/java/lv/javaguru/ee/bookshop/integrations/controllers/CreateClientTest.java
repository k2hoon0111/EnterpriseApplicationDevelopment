//package lv.javaguru.ee.deliveryagency.integrations.controllers;
//
//import lv.javaguru.ee.deliveryagency.integrations.domain.ClientDTO;
//import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;
//import lv.javaguru.ee.deliveryagency.integrations.jetty.EmbeddedJettyTest;
//import org.junit.Test;
//import org.springframework.http.ResponseEntity;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.hamcrest.MatcherAssert.assertThat;
//
///**
// * Created by Viktor on 16/09/2014.
// */
//public class CreateClientTest extends EmbeddedJettyTest {
//
//    @Test
//    public void testCreateDelivery() {
//        DeliveryDTO createDeliveryDTO = RestFixture.createDelivery();
//        assertThat(createDeliveryDTO.getDeliveryId(), is(notNullValue()));
//
//        ClientDTO clientDTO = new ClientDTO();
//        clientDTO.setFirstName("A");
//        clientDTO.setLastName("B");
//        clientDTO.setEmail("email");
//        clientDTO.setPhone("1111");
//        clientDTO.setSpecialNotes("notes");
//
//        ResponseEntity<ClientDTO> createClientResponse
//                = RestFixture.createClient(createDeliveryDTO.getDeliveryId(), clientDTO);
//        ClientDTO createClientDTO = createClientResponse.getBody();
//
//        assertThat(createClientDTO.getClientId(), is(notNullValue()));
//    }
//
//}
