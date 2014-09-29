package lv.javaguru.ee.warehouse.core.services;

import lv.javaguru.ee.warehouse.core.CommandExecutor;
import lv.javaguru.ee.warehouse.core.command.CreateOutgoingOrderCommand;
import lv.javaguru.ee.warehouse.core.command.CreateOutgoingOrderCommandResult;
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
public class CreateOutgoingOrderCommandHandlerTest extends DatabaseHibernateTest {
    
    @Autowired
    private CommandExecutor serviceExecutor;

    @Before
    public void setUp() {
        prepareDatabaseBeforeTest();    
    }
    
    @Test
    public void testCommandCreate() {       
        
        CreateOutgoingOrderCommand command = getCreateOutgoingOrderCommand(1, 10); 
        
        CreateOutgoingOrderCommandResult commandResult = serviceExecutor.execute(command);
        assertThat(commandResult, is(notNullValue()));
        assertThat(commandResult.getResult(), is(notNullValue()));
        assertThat(commandResult.getResult().getId(), is(notNullValue()));
    }

        
    private CreateOutgoingOrderCommand getCreateOutgoingOrderCommand(Integer quantity, Integer amount) { 
        Product product = getDefaultProduct();
        Warehouse warehouse = getDefaultWarehouse();
        return new CreateOutgoingOrderCommand(product, warehouse, quantity, amount);        
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
