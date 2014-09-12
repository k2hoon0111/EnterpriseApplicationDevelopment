package lv.javaguru.ee.bookshop.core.database.hibernate;

import lv.javaguru.ee.bookshop.core.domain.Book;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 09/09/14
 * Time: 09:38
 * To change this template use File | Settings | File Templates.
 */
public class BookDAOImplTest extends DatabaseHibernateTest {
    @Test
    public void testCreateBook() {
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Book book = createDefaultBook();
                Assert.assertNotNull(book);
                assertThat(book.getBookId(), is(nullValue()));
                saveBoook(book);
                assertThat(book.getBookId(), is(notNullValue()));
            }
        });

    }
}
