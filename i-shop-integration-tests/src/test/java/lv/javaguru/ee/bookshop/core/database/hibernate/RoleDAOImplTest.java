package lv.javaguru.ee.bookshop.core.database.hibernate;

import lv.javaguru.ee.bookshop.core.domain.Role;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 06/09/14
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public class RoleDAOImplTest extends DatabaseHibernateTest {
    @Test
    public void testCreateRole() {
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Role role = createDefaultRole();
                Assert.assertNotNull(role);
                assertThat(role.getRoleId(), is(nullValue()));
                saveRole(role);
                assertThat(role.getRoleId(), is(notNullValue()));
            }
        });

    }
}
