package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.*;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
* Created by MumboJumbo on 21/09/14.
*/

public class CreateOrderDetailTest extends EmbeddedJettyTest {

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
        Long createdOrderId =  createdOrderDTO.getOrderId();

        // Create orderDetails
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        orderDetailDTO.setBookId(createdBookId);
        orderDetailDTO.setOrderId(createdOrderId);
        orderDetailDTO.setQuantity(5);

        OrderDetailDTO createdOrderDetailDTO = RestFixture.createOrderDetail(createdBookId, createdOrderId, orderDetailDTO);

        MatcherAssert.assertThat(createdOrderDetailDTO.getOrderDetailId(), is(notNullValue()));
    }

}