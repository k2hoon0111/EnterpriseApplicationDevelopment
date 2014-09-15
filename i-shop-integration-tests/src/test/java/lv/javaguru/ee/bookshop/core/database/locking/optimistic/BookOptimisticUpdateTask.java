package lv.javaguru.ee.bookshop.core.database.locking.optimistic;

import lv.javaguru.ee.bookshop.core.database.BookDAO;
import lv.javaguru.ee.bookshop.core.domain.Book;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Random;
import java.util.concurrent.Callable;

import static com.google.common.base.Preconditions.checkNotNull;

public class BookOptimisticUpdateTask implements Callable<BookOptimisticUpdateTaskResult> {

    private PlatformTransactionManager transactionManager;
    private BookDAO bookDAO;
    private Long bookId;

    public BookOptimisticUpdateTask(PlatformTransactionManager transactionManager,
                                    BookDAO bookDAO,
                                    Long bookId) {
        this.transactionManager = transactionManager;
        this.bookDAO = bookDAO;
        this.bookId = bookId;
    }

    @Override
    public BookOptimisticUpdateTaskResult call() throws Exception {
        try {
            doInTransaction();
        } catch (Throwable e) {
            return new BookOptimisticUpdateTaskResult(false);
        }
        return new BookOptimisticUpdateTaskResult(true);
    }

    private void doInTransaction() {
        TransactionTemplate tt = new TransactionTemplate(transactionManager);
        tt.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Book book = bookDAO.getById(bookId);
                checkNotNull(book);

                Random rnd = new Random();
                Integer bookYear = rnd.nextInt(10000000);
                book.setYear(bookYear);

                bookDAO.update(book);
            }
        });
    }

}
