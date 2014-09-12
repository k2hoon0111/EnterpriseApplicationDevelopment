//package lv.javaguru.ee.deliveryagency.core.database.hibernate;
//
//import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
//import lv.javaguru.ee.deliveryagency.core.domain.DeliveryInfo;
//import org.junit.Test;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.TransactionCallbackWithoutResult;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.core.Is.is;
//import static org.hamcrest.core.IsNull.notNullValue;
//import static org.hamcrest.core.IsNull.nullValue;
//
//public class DeliveryInfoDAOImplTest extends DatabaseHibernateTest {
//
//	@Test
//	public void testCreate() {
//		doInTransaction(new TransactionCallbackWithoutResult() {
//			@Override
//			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
//				Delivery delivery = createDefaultDelivery();
//				DeliveryInfo deliveryInfo = getDefaultDeliveryInfo();
//				assertThat(deliveryInfo.getDeliveryInfoId(), is(nullValue()));
//				deliveryInfo.setDelivery(delivery);
//				deliveryInfoDAO.create(deliveryInfo);
//				assertThat(deliveryInfo.getDeliveryInfoId(), is(notNullValue()));
//			}
//		});
//	}
//
//}
