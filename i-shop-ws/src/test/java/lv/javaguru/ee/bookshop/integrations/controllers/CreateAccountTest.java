//package lv.javaguru.ee.bookshop.integrations.controllers;
//
//import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
//import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;
//import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
//import org.hamcrest.MatcherAssert;
//import org.junit.Test;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Date;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//
///**
// * Created by MumboJumbo on 22/09/14.
// */
//
//public class CreateAccountTest extends EmbeddedJettyTest {
//
//    @Test
//    public void testCreateAccount() {
//
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
//        MatcherAssert.assertThat(createAccountDTO.getAccountId(), is(notNullValue()));
//    }
//
//}
