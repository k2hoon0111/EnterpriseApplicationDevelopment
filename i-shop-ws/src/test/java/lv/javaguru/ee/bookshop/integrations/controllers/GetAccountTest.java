package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetAccountTest extends EmbeddedJettyTest {

    @Test
    public void testGetAccount() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setFirstName("First");
        accountDTO.setLastName("Last");
        accountDTO.setDateOfBirth(new Date());
        accountDTO.setEmailAddress("me@gmail.com");
        accountDTO.setUsername("user");
        accountDTO.setPassword("pass");
        accountDTO.setStreet("street");
        accountDTO.setHouseNumber("House");
        accountDTO.setBoxNumber("box");
        accountDTO.setCity("City");
        accountDTO.setPostalCode("postal");
        accountDTO.setCountry("country");
        AccountDTO createdAccountDTO = RestFixture.createAccount(accountDTO);

        MatcherAssert.assertThat(createdAccountDTO.getAccountId(), is(notNullValue()));

        Long accountId = createdAccountDTO.getAccountId();
        AccountDTO getAccountDTO = RestFixture.getAccount(accountId);

        MatcherAssert.assertThat(getAccountDTO.getAccountId(), is(accountId));
        MatcherAssert.assertThat(getAccountDTO.getFirstName(), is("First"));

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
