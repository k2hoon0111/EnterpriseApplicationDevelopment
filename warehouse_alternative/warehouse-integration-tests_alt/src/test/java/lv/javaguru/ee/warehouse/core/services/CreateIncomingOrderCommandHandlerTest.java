package lv.javaguru.ee.warehouse.core.services;

import java.util.concurrent.atomic.AtomicLong;
import lv.javaguru.ee.warehouse.core.CommandExecutor;
import lv.javaguru.ee.warehouse.core.command.CreateIncomingOrderCommand;
import lv.javaguru.ee.warehouse.core.command.CreateIncomingOrderCommandResult;
import lv.javaguru.ee.warehouse.core.database.hibernate.DatabaseHibernateTest;
import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

/**
 *
 * @author dell
 */
public class CreateIncomingOrderCommandHandlerTest extends DatabaseHibernateTest {
    
    
    @Autowired
    private CommandExecutor serviceExecutor;

    @Before
    public void setUp() {
        prepareDatabaseBeforeTest();    
    }
    
    @Test
    public void testCommandCreate() {       
        
        CreateIncomingOrderCommand command = getCreateIncomingOrderCommand(1, 10); 
        
        CreateIncomingOrderCommandResult commandResult = serviceExecutor.execute(command);
        assertThat(commandResult, is(notNullValue()));
        assertThat(commandResult.getResult(), is(notNullValue()));
        assertThat(commandResult.getResult().getId(), is(notNullValue()));
    }

        
    private CreateIncomingOrderCommand getCreateIncomingOrderCommand(Integer quantity, Integer amount) { 
        Product product = getDefaultProduct();
        Warehouse warehouse = getDefaultWarehouse();
        return new CreateIncomingOrderCommand(product, warehouse, quantity, amount);        
    }
    
    private void prepareDatabaseBeforeTest() {   
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                Warehouse warehouse = createDefaultWarehouse();
                Product product = createDefaultProduct();
            }
        });  
    }
          
}
