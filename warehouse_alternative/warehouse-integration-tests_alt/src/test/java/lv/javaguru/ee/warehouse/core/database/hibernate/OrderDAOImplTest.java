package lv.javaguru.ee.warehouse.core.database.hibernate;

import lv.javaguru.ee.warehouse.core.domain.Order;
import lv.javaguru.ee.warehouse.core.domain.Order.Direction;
import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

/**
 *
 * @author dell
 */
public class OrderDAOImplTest extends DatabaseHibernateTest {

    @Test
    public void testCreateOrder() {
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                
                Product product = createDefaultProduct();
                Warehouse warehouse = createDefaultWarehouse();
                
                Order order = getDefaultOrder(warehouse, product);
                order.setDirection(Direction.INCOMING);
                order.setAmount(10);
                order.setQuantity(1);
                orderDAO.create(order);
                assertThat(order.getId(), is(notNullValue()));

            }
        });
    }

}
