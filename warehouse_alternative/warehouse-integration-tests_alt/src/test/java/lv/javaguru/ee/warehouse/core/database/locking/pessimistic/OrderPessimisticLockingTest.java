package lv.javaguru.ee.warehouse.core.database.locking.pessimistic;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import lv.javaguru.ee.warehouse.core.database.hibernate.DatabaseHibernateTest;
import lv.javaguru.ee.warehouse.core.domain.Order;
import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

/**
 *
 * @author dell
 */
public class OrderPessimisticLockingTest extends DatabaseHibernateTest {
 
    @Test
    public void testOrderOptimisticLocking() throws ExecutionException, InterruptedException {
        final AtomicLong id = new AtomicLong();

        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                
                Product product = createDefaultProduct();
                Warehouse warehouse = createDefaultWarehouse();
                Order order = createDefaultOrder(warehouse, product);                                
                orderDAO.create(order);
                id.set(order.getId());
                
            }
        });

        runConcurrently(id.get(), 40);
    }

    private void runConcurrently(Long clientId, int threadsCount) throws InterruptedException, ExecutionException {
        List<OrderPessimisticUpdateTask> tasks = Lists.newArrayList();
        for(int i = 0; i < threadsCount; i++) {
            tasks.add(new OrderPessimisticUpdateTask(transactionManager, orderDAO, clientId));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
        List<Future<OrderPessimisticUpdateTaskResult>> taskResults = executorService.invokeAll(tasks);

        Set<Boolean> results = Sets.newHashSet();
        for(Future<OrderPessimisticUpdateTaskResult> taskResultFuture : taskResults) {
            OrderPessimisticUpdateTaskResult taskResult = taskResultFuture.get();
            results.add(taskResult.isUpdatedSuccessfully());
        }
        assertThat(results.contains(Boolean.FALSE), is(false));
        assertThat(results.contains(Boolean.TRUE), is(true));
    }
    
}
