package lv.javaguru.ee.deliveryagency.core.database.locking.optimistic;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lv.javaguru.ee.deliveryagency.core.database.hibernate.DatabaseHibernateTest;
import lv.javaguru.ee.deliveryagency.core.domain.Client;
import lv.javaguru.ee.deliveryagency.core.domain.Delivery;

public class ClientOptimisticLockingTest extends DatabaseHibernateTest {

	@Test
	public void testClientOptimisticLocking() throws ExecutionException, InterruptedException {
		final AtomicLong clientId = new AtomicLong();
		
		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				Delivery delivery = createDefaultDelivery();
				Client client = getDefaultClient(delivery);
				clientDAO.create(client);
				clientId.set(client.getClientId());
			}
		});
		
		runUpdateClientConcurrently(clientId.get(), 100);		
	}

	private void runUpdateClientConcurrently(Long clientId, int threadCount) throws InterruptedException, ExecutionException {
		List<ClientOptimisticUpdateTask> tasks = Lists.newArrayList();
		for(int i = 0; i < threadCount; i++) {
			tasks.add(new ClientOptimisticUpdateTask(transactionManager, clientDAO, clientId));
		}

		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		List<Future<ClientOptimisticUpdateTaskResult>> taskResults = executorService.invokeAll(tasks);

		Set<Boolean> results = Sets.newHashSet();
		int failCount = 0;
		int successCount = 0;
		
		for(Future<ClientOptimisticUpdateTaskResult> taskResultFuture : taskResults) {
			ClientOptimisticUpdateTaskResult taskResult = taskResultFuture.get();
			results.add(taskResult.isUpdatedSuccessfully());
			if(taskResult.isUpdatedSuccessfully()) {
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
