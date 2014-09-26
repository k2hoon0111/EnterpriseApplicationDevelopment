package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Created by nikoboro on 2014.09.19..
 */
public class UpdateAccountTest extends EmbeddedJettyTest {

    @Test
    public void testUpdateAccount() {
        // Create account
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

        MatcherAssert.assertThat(createdAccountDTO.getAccountId(), is(notNullValue()));
        MatcherAssert.assertThat(createdAccountDTO.getFirstName(), is("First"));

        // Update account
        AccountDTO accountDTOForUpdate = new AccountDTO();
        accountDTOForUpdate.setAccountId(createdAccountDTO.getAccountId());
        accountDTOForUpdate.setFirstName("Updated First");
        accountDTOForUpdate.setLastName("Last");
        accountDTOForUpdate.setDateOfBirth(new Date());
        accountDTOForUpdate.setEmailAddress("me@gmail.com");
        accountDTOForUpdate.setUsername("user");
        accountDTOForUpdate.setPassword("pass");
        accountDTOForUpdate.setStreet("street");
        accountDTOForUpdate.setHouseNumber("House");
        accountDTOForUpdate.setBoxNumber("box");
        accountDTOForUpdate.setCity("City");
        accountDTOForUpdate.setPostalCode("postal");
        accountDTOForUpdate.setCountry("country");

        RestFixture.updateAccount(accountDTOForUpdate);

        ResponseEntity<AccountDTO> getAccountResponse
                = RestFixture.getAccount(accountDTOForUpdate.getAccountId());

        AccountDTO getAccountDTO = getAccountResponse.getBody();
        MatcherAssert.assertThat(getAccountDTO.getAccountId(), CoreMatchers.is(accountDTOForUpdate.getAccountId()));
        MatcherAssert.assertThat(getAccountDTO.getFirstName(), is("Updated First"));
    }

}
