package lv.javaguru.ee.bookshop.core.controllers;

import lv.javaguru.ee.bookshop.core.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.core.jetty.EmbeddedJettyTest;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDTO;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
* Created by MumboJumbo on 20/09/14.
*/

public class CreateOrderTest extends EmbeddedJettyTest {

    @Test
    public void testCreateOrder() {

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

        MatcherAssert.assertThat(createdOrderDTO.getOrderId(), is(notNullValue()));
    }

}