package lv.javaguru.ee.bookshop.core.controllers;

import junit.framework.TestCase;
import lv.javaguru.ee.bookshop.core.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.core.domain.Account;
import lv.javaguru.ee.bookshop.core.jetty.EmbeddedJettyTest;
import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

/**
 * Created by MumboJumbo on 19/09/14.
 */

public class DeleteAccountTest extends EmbeddedJettyTest {

    @Test
    public void testDeleteAccount() {
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

        ResponseEntity<AccountDTO> createAccountResponse
                = RestFixture.createAccount(accountDTO);
        AccountDTO createdAccountDTO = createAccountResponse.getBody();

        Long accountId = createdAccountDTO.getAccountId();

        RestFixture.deleteAccount(accountId);

        try {
            RestFixture.getAccount(Long.valueOf(accountId));
        } catch (HttpClientErrorException e) {
            TestCase.assertEquals("Entity " + Account.class.getName() + " not found by id " + accountId, e.getResponseBodyAsString());
            TestCase.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
        }

    }

}
