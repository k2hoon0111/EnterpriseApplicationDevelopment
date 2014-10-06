package lv.javaguru.ee.bookshop.core.database.hibernate;

import lv.javaguru.ee.bookshop.core.domain.Order;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 09/09/14
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
public class OrderWithDetailsDAOImplTest extends DatabaseHibernateTest {
    @Test
    public void testCreateOrderWithDetails() {
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Order order = createDefaultOrder();
                Assert.assertNotNull(order);
                assertThat(order.getOrderId(), is(nullValue()));
                order.addOrderDetail(createDefaultOrderDetail());
                order.addOrderDetail(createDefaultOrderDetail());
                saveOrder(order);
                assertThat(order.getOrderId(), is(notNullValue()));

            }
        });

    }

}
