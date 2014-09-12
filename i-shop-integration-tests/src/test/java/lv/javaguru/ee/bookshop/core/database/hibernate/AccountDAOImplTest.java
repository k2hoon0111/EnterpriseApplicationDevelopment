package lv.javaguru.ee.bookshop.core.database.hibernate;

import lv.javaguru.ee.bookshop.core.domain.Account;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 */
public class AccountDAOImplTest extends DatabaseHibernateTest {

    @Test
    public void testCreateAccount() {
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Account account = createDefaultAccount();
                Assert.assertNotNull(account);
                assertThat(account.getAccountId(), is(nullValue()));
                saveAccount(account);
                assertThat(account.getAccountId(), is(notNullValue()));
            }
        });

    }
}
