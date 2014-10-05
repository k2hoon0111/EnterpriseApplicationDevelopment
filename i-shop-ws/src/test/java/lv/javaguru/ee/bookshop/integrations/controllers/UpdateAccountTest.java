package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by nikoboro on 2014.09.19..
 */
public class UpdateAccountTest extends EmbeddedJettyTest {

    @Test
    public void testUpdateAccount() {
        // Create account
        AccountDTO defaultAccountDTO = DefaultObjectsFixture.createDefaultAccount();

        AccountDTO createdAccountDTO = RestFixture.createAccount(defaultAccountDTO);

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

        RestFixture.updateAccount(accountDTOForUpdate.getAccountId(), accountDTOForUpdate);
        AccountDTO updatedAccount = RestFixture.getAccount(createdAccountDTO.getAccountId());

        MatcherAssert.assertThat(updatedAccount.getAccountId(), is(accountDTOForUpdate.getAccountId()));
        MatcherAssert.assertThat(updatedAccount.getFirstName(), is("Updated First"));
    }

}
