package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by MumboJumbo on 19/09/14.
 */

public class DeleteAccountTest extends EmbeddedJettyTest {

    @Test
    public void testDeleteAccount() {
        AccountDTO accountDTO = DefaultObjectsFixture.createDefaultAccount();

        AccountDTO createdAccountDTO = RestFixture.createAccount(accountDTO);

        Long createdAccountId = createdAccountDTO.getAccountId();

        RestFixture.deleteAccount(createdAccountId);

        try {
            RestFixture.getAccount(createdAccountId);
        } catch (RestException e) {
            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getHttpStatus());
        }


    }

}
