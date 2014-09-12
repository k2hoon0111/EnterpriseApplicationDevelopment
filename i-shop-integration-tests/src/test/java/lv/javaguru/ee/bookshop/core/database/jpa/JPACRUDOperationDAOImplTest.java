//package lv.javaguru.ee.bookshop.core.database.jpa;
//
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.*;
//
//import java.util.concurrent.atomic.AtomicLong;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.TransactionCallbackWithoutResult;
//
//import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
//
//public class JPACRUDOperationDAOImplTest extends DatabaseJPATest {
//
//	@Autowired
//	private JPACRUDOperationDAO jpaCrudOperationDAO;
//
//
//	@Test
//	public void testPersist() {
//		doInTransaction(new TransactionCallbackWithoutResult() {
//			@Override
//			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
//				Delivery delivery = new Delivery();
//				assertThat(delivery.getDeliveryId(), is(nullValue()));
//				jpaCrudOperationDAO.create(delivery);
//				assertThat(delivery.getDeliveryId(), is(notNullValue()));
//			}
//		});
//	}
//
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
//
//}
