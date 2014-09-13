package lv.javaguru.ee.bookshop.core.database.jpa;

import lv.javaguru.ee.bookshop.core.domain.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class JPACRUDOperationDAOImplTest extends DatabaseJPATest {

	@Autowired
	private JPACRUDOperationDAO jpaCrudOperationDAO;


	@Test
	public void testPersist() {
		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				Book book = new Book();
				assertThat(book.getBookId(), is(nullValue()));
				jpaCrudOperationDAO.create(book);
				assertThat(book.getBookId(), is(notNullValue()));
			}
		});
	}

//	@Test
//	public void testGetById() {
//		final AtomicLong deliveryId = new AtomicLong();
//		doInTransaction(new TransactionCallbackWithoutResult() {
//			@Override
//			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
//				Delivery delivery = new Delivery();
//				assertThat(delivery.getDeliveryId(), is(nullValue()));
//				jpaCrudOperationDAO.create(delivery);
//				deliveryId.set(delivery.getDeliveryId());
//			}
//		});
//		doInTransaction(new TransactionCallbackWithoutResult() {
//			@Override
//			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
//				Delivery delivery = jpaCrudOperationDAO.getById(Delivery.class, deliveryId.get());
//				assertThat(delivery, is(notNullValue()));
//			}
//		});
//	}

}
