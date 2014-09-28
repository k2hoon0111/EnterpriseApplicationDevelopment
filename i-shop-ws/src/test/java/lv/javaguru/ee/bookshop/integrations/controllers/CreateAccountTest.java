package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
* Created by MumboJumbo on 22/09/14.
*/

public class CreateAccountTest extends EmbeddedJettyTest {

    @Test
    public void testCreateAccount() {

        AccountDTO defaultAccountDTO = DefaultObjectsFixture.createDefaultAccount();

        AccountDTO createdAccountDTO = RestFixture.createAccount(defaultAccountDTO);

        MatcherAssert.assertThat(createdAccountDTO.getAccountId(), is(notNullValue()));
    }

}
