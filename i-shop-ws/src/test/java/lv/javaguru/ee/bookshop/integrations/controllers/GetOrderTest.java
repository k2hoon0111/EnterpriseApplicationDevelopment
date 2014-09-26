package lv.javaguru.ee.bookshop.integrations.controllers;

import junit.framework.TestCase;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.core.domain.Order;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDTO;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by MumboJumbo on 20/09/14.
 */

public class GetOrderTest extends EmbeddedJettyTest {

    @Test
    public void testGetOrder() {
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

        ResponseEntity<OrderDTO> createdOrderResponse
                = RestFixture.createOrder(orderDTO);
        OrderDTO createdOrderDTO = createdOrderResponse.getBody();

        Long orderId = createdOrderDTO.getOrderId();

        ResponseEntity<OrderDTO> getOrderResponse
                = RestFixture.getOrder(orderId);

        OrderDTO getOrderDTO = getOrderResponse.getBody();
        MatcherAssert.assertThat(getOrderDTO.getOrderId(), is(orderId));
        MatcherAssert.assertThat(getOrderDTO.getBillingStreet(), is("Street"));

    }

    @Test
    public void testGetOrderWithWrongId() {
        Long max = Long.MAX_VALUE;
        try {
            RestFixture.getOrder(max);
        } catch (HttpClientErrorException e) {
            TestCase.assertEquals("Entity " + Order.class.getName() + " not found by id " + max, e.getResponseBodyAsString());
            Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
        }
    }

}