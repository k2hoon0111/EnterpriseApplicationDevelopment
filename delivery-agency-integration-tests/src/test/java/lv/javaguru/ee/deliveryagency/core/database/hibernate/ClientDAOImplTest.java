package lv.javaguru.ee.deliveryagency.core.database.hibernate;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import lv.javaguru.ee.deliveryagency.core.domain.Client;
import lv.javaguru.ee.deliveryagency.core.domain.Delivery;

public class ClientDAOImplTest extends DatabaseHibernateTest {
	
	@Test
	public void testCreateClient() {
		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				Delivery delivery = createDefaultDelivery();
				Client client = getDefaultClient(delivery);
				assertThat(client.getClientId(), is(nullValue()));
				clientDAO.create(client);
				assertThat(client.getClientId(), is(notNullValue()));
			}
		});		
	}
	
}
