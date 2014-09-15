package lv.javaguru.ee.deliveryagency.core.database.locking.pessimistic;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lv.javaguru.ee.bookshop.core.database.hibernate.DatabaseHibernateTest;
import lv.javaguru.ee.bookshop.core.database.locking.pessimistic.BookPessimisticUpdateTask;
import lv.javaguru.ee.bookshop.core.database.locking.pessimistic.BookPessimisticUpdateTaskResult;
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

/**
 * Created by Viktor on 06/09/2014.
 */
public class BookPessimisticLockingTest extends DatabaseHibernateTest {

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

        runConcurrently(bookId.get(), 100);
    }

    private void runConcurrently(Long clientId, int threadsCount) throws InterruptedException, ExecutionException {
        List<BookPessimisticUpdateTask> tasks = Lists.newArrayList();
        for (int i = 0; i < threadsCount; i++) {
            tasks.add(new BookPessimisticUpdateTask(transactionManager, bookDAO, clientId));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
        List<Future<BookPessimisticUpdateTaskResult>> taskResults = executorService.invokeAll(tasks);

        Set<Boolean> results = Sets.newHashSet();
        for (Future<BookPessimisticUpdateTaskResult> taskResultFuture : taskResults) {
            BookPessimisticUpdateTaskResult taskResult = taskResultFuture.get();
            results.add(taskResult.isUpdatedSuccessfully());
        }
        assertThat(results.contains(Boolean.FALSE), is(false));
        assertThat(results.contains(Boolean.TRUE), is(true));
    }

}
