//package lv.javaguru.ee.deliveryagency.integrations.controllers;
//
//import static lv.javaguru.ee.deliveryagency.integrations.domain.builders.ClientDTOBuilder.*;
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.*;
//
//import org.junit.Test;
//
//import lv.javaguru.ee.deliveryagency.integrations.domain.ClientDTO;
//import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;
//import lv.javaguru.ee.deliveryagency.integrations.jetty.EmbeddedJettyTest;
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
//	    ClientDTO clientDTO = createClientDTO()
//			    .withFirstName("A")
//			    .withLastName("B")
//			    .withEmail("email")
//			    .withPhone("1111")
//			    .withSpecialNotes("notes").build();
//
//        ClientDTO createClientDTO = RestFixture.createClient(createDeliveryDTO.getDeliveryId(), clientDTO);
//        assertThat(createClientDTO.getClientId(), is(notNullValue()));
//    }
//
//}
