package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.*;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Created by MumboJumbo on 21/09/14.
 */

public class OrderDetailRESTTest extends EmbeddedJettyTest {

  @Test
  public void testCreateOrderDetail() {

    // Create book
    CategoryDTO categoryDTO = DefaultObjectsFixture.createDefaultCategory();
    CategoryDTO createdCategoryDTO = RestFixture.createCategory(categoryDTO);
    Long categoryId = createdCategoryDTO.getCategoryId();

    BookDTO bookDTO = DefaultObjectsFixture.createDefaultBook();
    BookDTO createdBookDTO = RestFixture.createBook(categoryId, bookDTO);

    Long createdBookId = createdBookDTO.getBookId();

    // Create order
    AccountDTO accountDTO = DefaultObjectsFixture.createDefaultAccount();

    AccountDTO createdAccountDTO = RestFixture.createAccount(accountDTO);
    Long accountId = createdAccountDTO.getAccountId();

    OrderDTO orderDTO = DefaultObjectsFixture.createDefaultOrder();

    OrderDTO createdOrderDTO = RestFixture.createOrder(accountId, orderDTO);
    Long createdOrderId = createdOrderDTO.getOrderId();

    // Create orderDetails
    OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

    orderDetailDTO.setBookId(createdBookId);
    orderDetailDTO.setOrderId(createdOrderId);
    orderDetailDTO.setQuantity(5);

    OrderDetailDTO createdOrderDetailDTO = RestFixture.createOrderDetail(createdBookId, createdOrderId, orderDetailDTO);

    MatcherAssert.assertThat(createdOrderDetailDTO.getOrderDetailId(), is(notNullValue()));
  }

  @Test
  public void testDeleteOrderDetail() {
    // Create book
    CategoryDTO categoryDTO = DefaultObjectsFixture.createDefaultCategory();
    CategoryDTO createdCategoryDTO = RestFixture.createCategory(categoryDTO);
    Long categoryId = createdCategoryDTO.getCategoryId();

    BookDTO bookDTO = DefaultObjectsFixture.createDefaultBook();
    BookDTO createdBookDTO = RestFixture.createBook(categoryId, bookDTO);

    Long createdBookId = createdBookDTO.getBookId();

    // Create order
    AccountDTO accountDTO = DefaultObjectsFixture.createDefaultAccount();

    AccountDTO createdAccountDTO = RestFixture.createAccount(accountDTO);
    Long accountId = createdAccountDTO.getAccountId();

    OrderDTO orderDTO = DefaultObjectsFixture.createDefaultOrder();

    OrderDTO createdOrderDTO = RestFixture.createOrder(accountId, orderDTO);
    Long createdOrderId = createdOrderDTO.getOrderId();

    // Create orderDetails
    OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

    orderDetailDTO.setBookId(createdBookId);
    orderDetailDTO.setOrderId(createdOrderId);
    orderDetailDTO.setQuantity(5);

    OrderDetailDTO createdOrderDetailDTO = RestFixture.createOrderDetail(createdBookId, createdOrderId, orderDetailDTO);

    Long createdOrderDetailId = createdOrderDetailDTO.getOrderDetailId();

    RestFixture.deleteOrderDetail(createdBookId, createdOrderId, createdOrderDetailId);

    try {
      RestFixture.getOrderDetail(createdBookId, createdOrderId, createdOrderDetailId);
    } catch (RestException e) {
      assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getHttpStatus());
    }

  }

  @Test
  public void testGetOrderDetail() {
    // Create book
    CategoryDTO defaultCategoryDTO = DefaultObjectsFixture.createDefaultCategory();
    CategoryDTO createdCategoryDTO = RestFixture.createCategory(defaultCategoryDTO);
    Long categoryId = createdCategoryDTO.getCategoryId();

    BookDTO defaultBookDTO = DefaultObjectsFixture.createDefaultBook();
    BookDTO createdBookDTO = RestFixture.createBook(categoryId, defaultBookDTO);

    Long createdBookId = createdBookDTO.getBookId();

    // Create order
    AccountDTO defaultAccountDTO = DefaultObjectsFixture.createDefaultAccount();

    AccountDTO createdAccountDTO = RestFixture.createAccount(defaultAccountDTO);
    Long accountId = createdAccountDTO.getAccountId();

    OrderDTO defaultOrderDTO = DefaultObjectsFixture.createDefaultOrder();

    OrderDTO createdOrderDTO = RestFixture.createOrder(accountId, defaultOrderDTO);
    Long createdOrderId = createdOrderDTO.getOrderId();

    // Create orderDetails
    OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

    orderDetailDTO.setBookId(createdBookId);
    orderDetailDTO.setOrderId(createdOrderId);
    orderDetailDTO.setQuantity(5);

    OrderDetailDTO createdOrderDetailDTO = RestFixture.createOrderDetail(createdBookId, createdOrderId, orderDetailDTO);

    Long createdOrderDetailId = createdOrderDetailDTO.getOrderDetailId();

    OrderDetailDTO getOrderDetailDTO = RestFixture.getOrderDetail(createdBookId, createdOrderId, createdOrderDetailId);

    MatcherAssert.assertThat(getOrderDetailDTO.getOrderId(), is(createdOrderId));
    MatcherAssert.assertThat(getOrderDetailDTO.getQuantity(), is(5));

  }

  @Test
  public void testUpdateOrderDetail() {
    // Create book
    CategoryDTO categoryDTO = DefaultObjectsFixture.createDefaultCategory();
    CategoryDTO createdCategoryDTO = RestFixture.createCategory(categoryDTO);
    Long categoryId = createdCategoryDTO.getCategoryId();

    BookDTO bookDTO = DefaultObjectsFixture.createDefaultBook();
    BookDTO createdBookDTO = RestFixture.createBook(categoryId, bookDTO);

    Long createdBookId = createdBookDTO.getBookId();

    // Create order
    AccountDTO accountDTO = DefaultObjectsFixture.createDefaultAccount();

    AccountDTO createdAccountDTO = RestFixture.createAccount(accountDTO);
    Long accountId = createdAccountDTO.getAccountId();

    OrderDTO orderDTO = DefaultObjectsFixture.createDefaultOrder();

    OrderDTO createdOrderDTO = RestFixture.createOrder(accountId, orderDTO);
    Long createdOrderId = createdOrderDTO.getOrderId();

    // Create orderDetails
    OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

    orderDetailDTO.setBookId(createdBookId);
    orderDetailDTO.setOrderId(createdOrderId);
    orderDetailDTO.setQuantity(5);

    OrderDetailDTO createdOrderDetailDTO = RestFixture.createOrderDetail(createdBookId, createdOrderId, orderDetailDTO);

    Long createdOrderDetailId = createdOrderDetailDTO.getOrderDetailId();

    // Update orderDetail
    OrderDetailDTO orderDetailDTOForUpdate = new OrderDetailDTO();
    orderDetailDTOForUpdate.setOrderDetailId(createdOrderDetailId);

    orderDetailDTOForUpdate.setBookId(createdBookId);
    orderDetailDTOForUpdate.setOrderId(createdOrderId);
    orderDetailDTOForUpdate.setQuantity(8);

    RestFixture.updateOrderDetail(createdBookId, createdOrderId, createdOrderDetailId, orderDetailDTOForUpdate);

    OrderDetailDTO updatedOrderDetail = RestFixture.getOrderDetail(createdBookId, createdOrderId, createdOrderDetailId);

    MatcherAssert.assertThat(updatedOrderDetail.getOrderDetailId(), CoreMatchers.is(orderDetailDTOForUpdate.getOrderDetailId()));
    MatcherAssert.assertThat(updatedOrderDetail.getQuantity(), is(8));
  }

}