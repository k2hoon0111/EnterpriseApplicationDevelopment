//package lv.javaguru.ee.bookshop.integrations.controllers;
//
//import junit.framework.TestCase;
//import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
//import lv.javaguru.ee.bookshop.core.domain.Account;
//import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
//import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;
//import org.hamcrest.MatcherAssert;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.HttpClientErrorException;
//
//import java.util.Date;
//
//import static org.hamcrest.CoreMatchers.is;
//
//public class GetAccountTest extends EmbeddedJettyTest {
//
//    @Test
//    public void testGetAccount() {
//        AccountDTO accountDTO = new AccountDTO();
//
//        accountDTO.setFirstName("First");
//        accountDTO.setLastName("Last");
//        accountDTO.setDateOfBirth(new Date());
//        accountDTO.setEmailAddress("me@gmail.com");
//        accountDTO.setUsername("user");
//        accountDTO.setPassword("pass");
//        accountDTO.setStreet("street");
//        accountDTO.setHouseNumber("House");
//        accountDTO.setBoxNumber("box");
//        accountDTO.setCity("City");
//        accountDTO.setPostalCode("postal");
//        accountDTO.setCountry("country");
//
//        ResponseEntity<AccountDTO> createAccountResponse
//                = RestFixture.createAccount(accountDTO);
//        AccountDTO createAccountDTO = createAccountResponse.getBody();
//
//        Long accountId = createAccountDTO.getAccountId();
//
//        ResponseEntity<AccountDTO> getAccountResponse
//                = RestFixture.getAccount(accountId);
//
//        AccountDTO getAccountDTO = getAccountResponse.getBody();
//        MatcherAssert.assertThat(getAccountDTO.getAccountId(), is(accountId));
//        MatcherAssert.assertThat(getAccountDTO.getFirstName(), is("First"));
//
//    }
//
//    @Test
//    public void testGetOrderWithWrongId() {
//        Long max = Long.MAX_VALUE;
//        try {
//            RestFixture.getAccount(max);
//        } catch (HttpClientErrorException e) {
//            TestCase.assertEquals("Entity " + Account.class.getName() + " not found by id " + max, e.getResponseBodyAsString());
//            Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
//        }
//    }
//
//}
