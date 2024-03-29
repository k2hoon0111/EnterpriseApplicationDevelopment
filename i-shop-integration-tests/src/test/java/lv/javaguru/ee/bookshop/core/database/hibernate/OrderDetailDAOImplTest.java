package lv.javaguru.ee.bookshop.core.database.hibernate;

import lv.javaguru.ee.bookshop.core.domain.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
* Created with IntelliJ IDEA.
* User: MumboJumbo
* Date: 10/09/14
* Time: 19:05
* To change this template use File | Settings | File Templates.
*/
public class OrderDetailDAOImplTest extends DatabaseHibernateTest {
    @Test
    public void testCreateOrderDetail() {
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                OrderDetail orderDetail = createDefaultOrderDetail();
                Assert.assertNotNull(orderDetail);
                assertThat(orderDetail.getOrderDetailId(), is(nullValue()));
                saveOrderDetail(orderDetail);
                assertThat(orderDetail.getOrderDetailId(), is(notNullValue()));
            }
        });
    }
}
