package lv.javaguru.ee.bookshop.integrations.controllers;

import junit.framework.TestCase;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.core.domain.Order;
import lv.javaguru.ee.bookshop.core.jetty.EmbeddedJettyTest;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDTO;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

/**
 * Created by MumboJumbo on 21/09/14.
 */

public class DeleteOrderTest extends EmbeddedJettyTest {

    @Test
    public void testDeleteOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAccountId(Long.valueOf(1));

        orderDTO.setShippingStreet("Street");
        orderDTO.setShippingHouseNumber("6");
        orderDTO.setShippingBoxNumber("4");
        orderDTO.setShippingCity("City");
        orderDTO.setShippingPostalCode("Postal");
        orderDTO.setShippingCountry("Country");
        orderDTO.setBillingStreet("Street");
        orderDTO.setBillingHouseNumber("6");
        orderDTO.setBillingBoxNumber("4");
        orderDTO.setBillingCity("City");
        orderDTO.setBillingoPostalCode("666");
        orderDTO.setBillingCountry("Country");
        orderDTO.setBillingSameAsShipping(false);
        orderDTO.setDeliveryDate(new Date());
        orderDTO.setOrderDate(new Date());


        ResponseEntity<OrderDTO> createOrderResponse
                = RestFixture.createOrder(orderDTO);
        OrderDTO createdOrderDTO = createOrderResponse.getBody();

        Long orderId = createdOrderDTO.getOrderId();

        RestFixture.deleteOrder(orderId);


        try {
            RestFixture.getOrder(Long.valueOf(orderId));
        } catch (HttpClientErrorException e) {
            TestCase.assertEquals("Entity " + Order.class.getName() + " not found by id " + orderId, e.getResponseBodyAsString());
            TestCase.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
        }

    }

}
