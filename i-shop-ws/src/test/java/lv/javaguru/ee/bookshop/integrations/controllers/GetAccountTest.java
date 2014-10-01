package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetAccountTest extends EmbeddedJettyTest {

    @Test
    public void testGetAccount() {
        AccountDTO accountDTO = DefaultObjectsFixture.createDefaultAccount();

        AccountDTO createdAccountDTO = RestFixture.createAccount(accountDTO);

        MatcherAssert.assertThat(createdAccountDTO.getAccountId(), is(notNullValue()));

        Long accountId = createdAccountDTO.getAccountId();
        AccountDTO getAccountDTO = RestFixture.getAccount(accountId);

        MatcherAssert.assertThat(getAccountDTO.getAccountId(), is(accountId));
        MatcherAssert.assertThat(getAccountDTO.getFirstName(), is(accountDTO.getFirstName()));

    }

//    @Test
//    public void testGetOrderWithWrongId() {
////        try {
////            RestFixture.getAccount(Long.MAX_VALUE);
////        } catch (RestException e) {
////            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getHttpStatus());
////        }
//    }

}
