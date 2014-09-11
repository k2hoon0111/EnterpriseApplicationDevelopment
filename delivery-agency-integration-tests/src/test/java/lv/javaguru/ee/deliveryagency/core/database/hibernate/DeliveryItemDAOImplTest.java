package lv.javaguru.ee.deliveryagency.core.database.hibernate;

import lv.javaguru.ee.deliveryagency.core.database.DeliveryItemDAO;
import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
import lv.javaguru.ee.deliveryagency.core.domain.DeliveryItem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import java.math.BigDecimal;

import static lv.javaguru.ee.deliveryagency.core.domain.DeliveryItemBuilder.createDeliveryItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Viktor on 02/08/2014.
 */
public class DeliveryItemDAOImplTest extends DatabaseHibernateTest {

    @Autowired
    private DeliveryItemDAO deliveryItemDAO;

    @Test
    public void testCreate() {
	    doInTransaction(new TransactionCallbackWithoutResult() {
		    @Override
		    protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
			    Delivery delivery = createDefaultDelivery();

			    DeliveryItem item = createDeliveryItem()
					    .with(delivery)
					    .withProductTitle("Samsung A1")
					    .withProductWeightInGram(100)
					    .withProductPrice(BigDecimal.ONE)
					    .withProductDeliveryPrice(BigDecimal.ONE).build();
			    assertThat(item.getDeliveryItemId(), is(nullValue()));
			    deliveryItemDAO.create(item);
			    assertThat(item.getDeliveryItemId(), is(notNullValue()));
		    }
	    });	    	    
    }

}
