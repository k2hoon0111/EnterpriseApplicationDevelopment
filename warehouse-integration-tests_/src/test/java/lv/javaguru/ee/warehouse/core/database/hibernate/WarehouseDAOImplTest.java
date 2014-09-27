package lv.javaguru.ee.warehouse.core.database.hibernate;

import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;
import lv.javaguru.ee.warehouse.core.domain.WarehouseAddress;
import lv.javaguru.ee.warehouse.core.domain.WarehouseProduct;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

/**
 *
 * @author dell
 */
//@Ignore
public class WarehouseDAOImplTest extends DatabaseHibernateTest {

    @Test    
    public void testCreateWarehouse() {
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                                
                Warehouse warehouse = getDefaultWarehouse(); 
                warehouse.setDescription("description");
                WarehouseAddress wa = getDefaultWarehouseAddress();
                warehouse.setAddress(wa);
                
                warehouseDAO.create(warehouse);
                assertThat(warehouse.getId(), is(notNullValue()));

            }
        });
    }
        
    @Test
    public void testCreateWarehouseProduct() {
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                                               
                Product product = createDefaultProduct();
                Warehouse warehouse = getDefaultWarehouse();                  
                                
                WarehouseProduct wp = getDefaultWarehouseProduct(warehouse, product);  
            
                warehouseDAO.create(warehouse);
                
                assertThat(warehouse.getId(), is(notNullValue()));
                assertThat(product.getId(), is(notNullValue()));
                assertThat(product.getWarehouseProducts().size(), is(1));                
            }
        });
    }
     
    @Test
    public void testAddProduct() {
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                                              
                Product product = createDefaultProduct();
                Warehouse warehouse = getDefaultWarehouse();                  
               
                warehouse.addProduct(product, 1, 10);
                
                warehouseDAO.create(warehouse);
                
                assertThat(warehouse.getId(), is(notNullValue()));
                assertThat(product.getId(), is(notNullValue()));
                assertThat(product.getWarehouseProducts().size(), is(1));   
                assertThat(warehouse.getWarehouseProducts().get(0).getCount(), is(1));
                assertThat(warehouse.getWarehouseProducts().get(0).getPrice(), is(10));
            }
        });
    }
    
}
