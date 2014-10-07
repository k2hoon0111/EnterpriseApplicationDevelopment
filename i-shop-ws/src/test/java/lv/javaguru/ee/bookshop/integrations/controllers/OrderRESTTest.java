package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Created by MumboJumbo on 20/09/14.
 */
public class OrderRESTTest extends EmbeddedJettyTest {

  @Test
  public void testCreateOrder() {

    AccountDTO accountDTO = DefaultObjectsFixture.createDefaultAccount();

    AccountDTO createdAccountDTO = RestFixture.createAccount(accountDTO);
    Long accountId = createdAccountDTO.getAccountId();

    OrderDTO orderDTO = DefaultObjectsFixture.createDefaultOrder();

    OrderDTO createdOrderDTO = RestFixture.createOrder(accountId, orderDTO);
    MatcherAssert.assertThat(createdOrderDTO.getOrderId(), is(notNullValue()));
  }

  @Test
  public void testGetOrder() {
    AccountDTO defaultAccountDTO = DefaultObjectsFixture.createDefaultAccount();

    AccountDTO createdAccountDTO = RestFixture.createAccount(defaultAccountDTO);
    Long accountId = createdAccountDTO.getAccountId();

    OrderDTO defaultOrderDTO = DefaultObjectsFixture.createDefaultOrder();

    OrderDTO createdOrderDTO = RestFixture.createOrder(accountId, defaultOrderDTO);

    Long createdOrderId = createdOrderDTO.getOrderId();

    OrderDTO getOrderDTO = RestFixture.getOrder(accountId, createdOrderId);

    MatcherAssert.assertThat(getOrderDTO.getOrderId(), is(createdOrderId));
    MatcherAssert.assertThat(getOrderDTO.getBillingStreet(), is(defaultOrderDTO.getBillingStreet()));

  }

  @Test
  public void testDeleteOrder() {
    AccountDTO accountDTO = DefaultObjectsFixture.createDefaultAccount();

    AccountDTO createdAccountDTO = RestFixture.createAccount(accountDTO);
    Long createdAccountId = createdAccountDTO.getAccountId();

    OrderDTO orderDTO = DefaultObjectsFixture.createDefaultOrder();

    OrderDTO createdOrderDTO = RestFixture.createOrder(createdAccountId, orderDTO);

    Long createdOrderId = createdOrderDTO.getOrderId();

    RestFixture.deleteOrder(createdAccountId, createdOrderId);

    try {
      RestFixture.getOrder(createdAccountId, createdOrderId);
    } catch (RestException e) {
      assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getHttpStatus());
    }

  }

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