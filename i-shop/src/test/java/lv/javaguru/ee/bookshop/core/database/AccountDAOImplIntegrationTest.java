//package lv.javaguru.ee.bookshop.core.database;
//
//import lv.javaguru.ee.bookshop.core.domain.Account;
//
//import org.junit.Assert;
//import org.junit.Test;
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//
///**
// * Created with IntelliJ IDEA.
// * User: MumboJumbo
// * Date: 05/09/14
// * Time: 14:36
// * To change this template use File | Settings | File Templates.
// */
//public class AccountDAOImplIntegrationTest extends DatabaseIntegrationTest {
//
//    @Test
//    public void testCreateAccount() {
//        Account account = createDefaultAccount();
//        Assert.assertNotNull(account);
//        assertThat(account.getAccountId(), is(nullValue()));
//        saveAccount(account);
//        assertThat(account.getAccountId(), is(notNullValue()));
//    }
//}
