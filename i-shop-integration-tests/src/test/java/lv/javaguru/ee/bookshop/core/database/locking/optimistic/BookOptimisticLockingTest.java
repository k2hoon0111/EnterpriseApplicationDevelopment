package lv.javaguru.ee.bookshop.core.database.locking.optimistic;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lv.javaguru.ee.bookshop.core.database.hibernate.DatabaseHibernateTest;

import lv.javaguru.ee.bookshop.core.domain.Book;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BookOptimisticLockingTest extends DatabaseHibernateTest {

	@Test
	public void testClientOptimisticLocking() throws ExecutionException, InterruptedException {
		final AtomicLong bookId = new AtomicLong();

		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Book book = createDefaultBook();
//                saveBoook(book);
                bookDAO.create(book);
                bookId.set(book.getBookId());
			}
		});

		runUpdateBookConcurrently(bookId.get(), 100);
	}

	private void runUpdateBookConcurrently(Long clientId, int threadCount) throws InterruptedException, ExecutionException {
		List<BookOptimisticUpdateTask> tasks = Lists.newArrayList();
		for(int i = 0; i < threadCount; i++) {
			tasks.add(new BookOptimisticUpdateTask(transactionManager, bookDAO, clientId));
		}

		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		List<Future<BookOptimisticUpdateTaskResult>> taskResults = executorService.invokeAll(tasks);

		Set<Boolean> results = Sets.newHashSet();
		for(Future<BookOptimisticUpdateTaskResult> taskResultFuture : taskResults) {
			BookOptimisticUpdateTaskResult taskResult = taskResultFuture.get();
			results.add(taskResult.isUpdatedSuccessfully());
		}
		assertThat(results.contains(Boolean.FALSE), is(true));
		assertThat(results.contains(Boolean.TRUE), is(true));
	}

}
