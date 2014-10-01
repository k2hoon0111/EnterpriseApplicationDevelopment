package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by MumboJumbo on 20/09/14.
 */

public class GetOrderTest extends EmbeddedJettyTest {

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

//    @Test
//    public void testGetOrderWithWrongId() {
////        try {
////            RestFixture.getOrder(Long.MAX_VALUE);
////        } catch (RestException e) {
////            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getHttpStatus());
////        }
//    }

}