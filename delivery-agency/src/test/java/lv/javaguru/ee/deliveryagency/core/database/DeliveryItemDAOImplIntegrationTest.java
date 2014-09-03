package lv.javaguru.ee.deliveryagency.core.database;

import static lv.javaguru.ee.deliveryagency.core.domain.DeliveryItemBuilder.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
import lv.javaguru.ee.deliveryagency.core.domain.DeliveryItem;

/**
 * Created by Viktor on 02/08/2014.
 */
public class DeliveryItemDAOImplIntegrationTest extends DatabaseIntegrationTest {

    @Autowired
    private DeliveryItemDAO deliveryItemDAO;

    @Test
    public void testCreate() {
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

}
