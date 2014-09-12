package lv.javaguru.ee.bookshop.core.database.hibernate;

import lv.javaguru.ee.bookshop.core.domain.Permission;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 06/09/14
 * Time: 23:59
 * To change this template use File | Settings | File Templates.
 */
public class PermissionDAOImplTest extends DatabaseHibernateTest {

    @Test
    public void testCreatePermission() {
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Permission permission = createDefaultPermission();
                assertThat(permission.getPermissionId(), is(nullValue()));
                savePermission(permission);
                assertThat(permission.getPermissionId(), is(notNullValue()));
            }
        });

    }
}