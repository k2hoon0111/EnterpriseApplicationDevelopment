package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
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

/**
 * Created by MumboJumbo on 22/09/14.
 */

public class AccountRESTTest extends EmbeddedJettyTest {

  @Test
  public void testCreateAccount() {

    AccountDTO defaultAccountDTO = DefaultObjectsFixture.createDefaultAccount();

    AccountDTO createdAccountDTO = RestFixture.createAccount(defaultAccountDTO);

    MatcherAssert.assertThat(createdAccountDTO.getAccountId(), is(notNullValue()));
  }

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
