package lv.javaguru.ee.warehouse.core.database.locking.optimistic;

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
public class OrderOptimisticLockingTest extends DatabaseHibernateTest {
 
    
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

        runUpdateClientConcurrently(id.get(), 100);
    }

    private void runUpdateClientConcurrently(Long clientId, int threadCount) throws InterruptedException, ExecutionException {
        List<OrderOptimisticUpdateTask> tasks = Lists.newArrayList();
        for (int i = 0; i < threadCount; i++) {
            tasks.add(new OrderOptimisticUpdateTask(transactionManager, orderDAO, clientId));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<Future<OrderOptimisticUpdateTaskResult>> taskResults = executorService.invokeAll(tasks);

        Set<Boolean> results = Sets.newHashSet();
        int failCount = 0;
        int successCount = 0;

        for (Future<OrderOptimisticUpdateTaskResult> taskResultFuture : taskResults) {
            OrderOptimisticUpdateTaskResult taskResult = taskResultFuture.get();
            results.add(taskResult.isUpdatedSuccessfully());
            if (taskResult.isUpdatedSuccessfully()) {
                successCount++;
            } else {
                failCount++;
            }
        }
        assertThat(results.contains(Boolean.FALSE), is(true));
        assertThat(results.contains(Boolean.TRUE), is(true));
        System.out.println("Success = " + successCount);
        System.out.println("Fail = " + failCount);
    }
    
}
