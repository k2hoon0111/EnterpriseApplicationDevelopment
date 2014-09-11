package lv.javaguru.ee.deliveryagency.core.database.hibernate;

import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
import lv.javaguru.ee.deliveryagency.core.domain.DeliveryAddress;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeliveryAddressDAOImplTest extends DatabaseHibernateTest {

	@Test
	public void testCreateDeliveryAddress() {
		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				Delivery delivery = createDefaultDelivery();
				DeliveryAddress deliveryAddress = getDefaultDeliveryAddress(delivery);
				assertThat(deliveryAddress.getDeliveryAddressId(), is(nullValue()));
				deliveryAddressDAO.create(deliveryAddress);
				assertThat(deliveryAddress.getDeliveryAddressId(), is(notNullValue()));
			}
		});
	}

}
