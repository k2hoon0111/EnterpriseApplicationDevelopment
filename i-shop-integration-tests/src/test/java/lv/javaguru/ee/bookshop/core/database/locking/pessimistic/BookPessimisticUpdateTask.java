package lv.javaguru.ee.bookshop.core.database.locking.pessimistic;

import lv.javaguru.ee.bookshop.core.database.BookDAO;
import lv.javaguru.ee.bookshop.core.domain.Book;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Random;
import java.util.concurrent.Callable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Viktor on 06/09/2014.
 */
public class BookPessimisticUpdateTask implements Callable<BookPessimisticUpdateTaskResult> {

    static Logger log = Logger.getLogger(BookPessimisticUpdateTask.class);

    private PlatformTransactionManager transactionManager;
    private BookDAO bookDAO;
    private Long bookId;

    public BookPessimisticUpdateTask(PlatformTransactionManager transactionManager,
                                     BookDAO bookDAO,
                                     Long bookId) {
        this.transactionManager = transactionManager;
        this.bookDAO = bookDAO;
        this.bookId = bookId;
    }

    @Override
    public BookPessimisticUpdateTaskResult call() throws Exception {
        try {
            log.info("Start pessimistic update");
            doInTransaction();
            log.info("Book UNLOCKED");
            log.info("End pessimistic update");
        } catch (Throwable e) {
            return new BookPessimisticUpdateTaskResult(false);
        }
        return new BookPessimisticUpdateTaskResult(true);
    }

    private void doInTransaction() {
        TransactionTemplate tt = new TransactionTemplate(transactionManager);
        tt.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Book book = bookDAO.getById(bookId, new LockOptions(LockMode.PESSIMISTIC_WRITE));
                checkNotNull(book);

                log.info("Book LOCKED");

                Random rnd = new Random();
                Integer bookYear = rnd.nextInt(10000000);
                book.setYear(bookYear);

                bookDAO.update(book);
            }
        });
    }

}
