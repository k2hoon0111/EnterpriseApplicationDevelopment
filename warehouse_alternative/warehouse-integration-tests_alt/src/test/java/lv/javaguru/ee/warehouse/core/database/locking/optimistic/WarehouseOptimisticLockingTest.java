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
public class WarehouseOptimisticLockingTest extends DatabaseHibernateTest {
    
    @Test
    public void testProductOptimisticLocking() throws ExecutionException, InterruptedException {
        final AtomicLong id = new AtomicLong();

        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {                
                Warehouse warehouse = createDefaultWarehouse();                                
                warehouseDAO.create(warehouse);
                id.set(warehouse.getId());                
            }
        });

        runUpdateClientConcurrently(id.get(), 40);
    }

    private void runUpdateClientConcurrently(Long clientId, int threadCount) throws InterruptedException, ExecutionException {
        List<WarehouseOptimisticUpdateTask> tasks = Lists.newArrayList();
        for (int i = 0; i < threadCount; i++) {
            tasks.add(new WarehouseOptimisticUpdateTask(transactionManager, warehouseDAO, clientId));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<Future<WarehouseOptimisticUpdateTaskResult>> taskResults = executorService.invokeAll(tasks);

        Set<Boolean> results = Sets.newHashSet();
        int failCount = 0;
        int successCount = 0;

        for (Future<WarehouseOptimisticUpdateTaskResult> taskResultFuture : taskResults) {
            WarehouseOptimisticUpdateTaskResult taskResult = taskResultFuture.get();
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
