package lv.javaguru.ee.warehouse.core.database.hibernate;

import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.ProductProperties;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

/**
 *
 * @author dell
 */
public class ProductPropertiesDAOImplTest extends DatabaseHibernateTest {
    
     @Test
    public void testCreateProductProperties() {
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                
                Product product = createDefaultProduct();
                ProductProperties pp = getDefaultProductProperties(product);
                productPropertiesDAO.create(pp);
                
                assertThat(pp.getId(), is(notNullValue()));

            }
        });
    }
    
}
