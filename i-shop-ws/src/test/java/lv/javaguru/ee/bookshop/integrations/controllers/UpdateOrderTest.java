package lv.javaguru.ee.bookshop.integrations.controllers;

/**
 * Created by MumboJumbo on 21/09/14.
 */

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by nikoboro on 2014.09.19..
 */
public class UpdateOrderTest extends EmbeddedJettyTest {

    @Test
    public void testUpdateOrder() {
        AccountDTO accountDTO = DefaultObjectsFixture.createDefaultAccount();

        AccountDTO createdAccountDTO = RestFixture.createAccount(accountDTO);
        Long createdAccountId = createdAccountDTO.getAccountId();

        OrderDTO orderDTO = DefaultObjectsFixture.createDefaultOrder();

        OrderDTO createdOrderDTO = RestFixture.createOrder(createdAccountId, orderDTO);

        // Update order
        OrderDTO orderDTOForUpdate = new OrderDTO();
        orderDTOForUpdate.setOrderId(createdOrderDTO.getOrderId());
        orderDTOForUpdate.setAccountId(createdAccountId);
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

        RestFixture.updateOrder(createdAccountId, createdOrderDTO.getOrderId(), orderDTOForUpdate);

        OrderDTO updatedOrder = RestFixture.getOrder(createdAccountId, createdOrderDTO.getOrderId());

        MatcherAssert.assertThat(updatedOrder.getOrderId(), CoreMatchers.is(orderDTOForUpdate.getOrderId()));
        MatcherAssert.assertThat(updatedOrder.getShippingStreet(), CoreMatchers.is("Updated Street"));
    }

}

