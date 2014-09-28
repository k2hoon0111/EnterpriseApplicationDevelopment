package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.*;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by MumboJumbo on 20/09/14.
 */

public class GetOrderDetailTest extends EmbeddedJettyTest {

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
    public void testGetOrderWithWrongId() {
        try {
            RestFixture.getCategory(Long.MAX_VALUE);
        } catch (RestException e) {
            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getHttpStatus());
        }
    }

}