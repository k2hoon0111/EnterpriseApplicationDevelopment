package lv.javaguru.ee.bookshop.core.database.audit;

import lv.javaguru.ee.bookshop.core.database.hibernate.DatabaseHibernateTest;
import lv.javaguru.ee.bookshop.core.domain.Book;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BooksAuditTest extends DatabaseHibernateTest {

    @Test
    public void testBookRevisions() {
        final AtomicLong bookId = new AtomicLong();
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Book book = createDefaultBook();
                saveBoook(book);
                bookDAO.create(book);
                bookId.set(book.getBookId());
            }
        });

        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                AuditReader reader = AuditReaderFactory.get(sessionFactory.getCurrentSession());
                List<Number> revisions = reader.getRevisions(Book.class, bookId.get());
                assertThat(revisions.isEmpty(), is(false));
                assertThat(revisions.size(), is(1));
            }
        });

    }

    @Test
    public void testGetBookAtRevision() {
        final AtomicLong bookId = new AtomicLong();
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Book book = createDefaultBook();
                saveBoook(book);
                bookDAO.create(book);
                bookId.set(book.getBookId());
            }
        });

        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Book book = bookDAO.getById(bookId.get());
                book.setYear(2000);
                bookDAO.update(book);
            }
        });

        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Book book = bookDAO.getById(bookId.get());
                book.setYear(1999);
                bookDAO.update(book);
            }
        });

        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                AuditReader reader = AuditReaderFactory.get(sessionFactory.getCurrentSession());
                List<Number> revisions = reader.getRevisions(Book.class, bookId.get());
                assertThat(revisions.size(), is(3));

                Book bookAtRevision = (Book) reader.createQuery()
                        .forEntitiesAtRevision(Book.class, revisions.get(1))
                        .add(AuditEntity.id().eq(bookId.get()))
                        .getSingleResult();
                assertThat(bookAtRevision.getYear(), is(2000));

                bookAtRevision = (Book) reader.createQuery()
                        .forEntitiesAtRevision(Book.class, revisions.get(2))
                        .add(AuditEntity.id().eq(bookId.get()))
                        .getSingleResult();
                assertThat(bookAtRevision.getYear(), is(1999));
            }
        });
    }
}
