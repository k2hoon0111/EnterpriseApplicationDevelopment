package lv.javaguru.ee.bookshop.integrations.controllers;

/**
 * Created by MumboJumbo on 21/09/14.
 */
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDTO;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Created by nikoboro on 2014.09.19..
 */
public class UpdateOrderTest extends EmbeddedJettyTest {

    @Test
    public void testUpdateOrder() {
        // Create order
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
        MatcherAssert.assertThat(createdOrderDTO.getShippingStreet(), is("Street"));

        // Update order
        OrderDTO orderDTOForUpdate = new OrderDTO();
        orderDTOForUpdate.setOrderId(createdOrderDTO.getOrderId());
        orderDTOForUpdate.setAccountId(Long.valueOf(1));
        orderDTOForUpdate.setShippingStreet("Updated Street");
        orderDTOForUpdate.setShippingHouseNumber("6");
        orderDTOForUpdate.setShippingBoxNumber("4");
        orderDTOForUpdate.setShippingCity("City");
        orderDTOForUpdate.setShippingPostalCode("Postal");
        orderDTOForUpdate.setShippingCountry("Country");
        orderDTOForUpdate.setBillingStreet("Street");
        orderDTOForUpdate.setBillingHouseNumber("6");
        orderDTOForUpdate.setBillingBoxNumber("4");
        orderDTOForUpdate.setBillingCity("City");
        orderDTOForUpdate.setBillingoPostalCode("666");
        orderDTOForUpdate.setBillingCountry("Country");
        orderDTOForUpdate.setBillingSameAsShipping(false);
        orderDTOForUpdate.setDeliveryDate(new Date());
        orderDTOForUpdate.setOrderDate(new Date());

        RestFixture.updateOrder(orderDTOForUpdate);


        ResponseEntity<OrderDTO> getOrderResponse
                = RestFixture.getOrder(orderDTOForUpdate.getOrderId());

        OrderDTO getOrderDTO = getOrderResponse.getBody();
        MatcherAssert.assertThat(getOrderDTO.getOrderId(), CoreMatchers.is(orderDTOForUpdate.getOrderId()));
        MatcherAssert.assertThat(getOrderDTO.getShippingStreet(), is("Updated Street"));
    }

}

