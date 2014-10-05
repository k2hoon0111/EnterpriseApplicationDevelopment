package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by MumboJumbo on 21/09/14.
 */

public class DeleteOrderTest extends EmbeddedJettyTest {

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

}
