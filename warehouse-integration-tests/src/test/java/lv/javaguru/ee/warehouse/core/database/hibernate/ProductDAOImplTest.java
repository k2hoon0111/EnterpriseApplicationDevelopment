package lv.javaguru.ee.warehouse.core.database.hibernate;

import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;
import lv.javaguru.ee.warehouse.core.domain.WarehouseProduct;
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
public class ProductDAOImplTest extends DatabaseHibernateTest {
        
    @Test
    public void testCreateProduct() {
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                
                Product product = getDefaultProduct();
                productDAO.create(product);
                
                assertThat(product.getId(), is(notNullValue()));

            }
        });
    }
      
    
    @Test
    public void testCreateWarehouseProduct() {
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                                
                Warehouse warehouse = createDefaultWarehouse();                  
                Product product = getDefaultProduct(); 
                
                WarehouseProduct wp = getDefaultWarehouseProduct(warehouse, product);  
                                
                productDAO.create(product);
                
                assertThat(warehouse.getId(), is(notNullValue()));
                assertThat(product.getId(), is(notNullValue()));
                assertThat(warehouse.getWarehouseProducts().size(), is(1));      
                
            }
        });
    }
       
    @Test
    public void testAddToWarehouse() {
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                                
                Warehouse warehouse = createDefaultWarehouse();                  
                Product product = getDefaultProduct();
               
                product.addToWarehouse(warehouse, 1, 10);
                
                productDAO.create(product);
                
                assertThat(warehouse.getId(), is(notNullValue()));
                assertThat(product.getId(), is(notNullValue()));
                assertThat(product.getWarehouseProducts().size(), is(1));   
                assertThat(warehouse.getWarehouseProducts().get(0).getCount(), is(1));
                assertThat(warehouse.getWarehouseProducts().get(0).getPrice(), is(10));
            }
        });
    }
    
}
