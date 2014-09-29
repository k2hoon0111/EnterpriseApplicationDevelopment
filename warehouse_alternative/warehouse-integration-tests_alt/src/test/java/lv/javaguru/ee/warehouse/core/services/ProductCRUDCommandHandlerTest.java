package lv.javaguru.ee.warehouse.core.services;

import java.util.concurrent.atomic.AtomicLong;
import lv.javaguru.ee.warehouse.core.CommandExecutor;
import lv.javaguru.ee.warehouse.core.command.Action;
import static lv.javaguru.ee.warehouse.core.command.Action.*;
import lv.javaguru.ee.warehouse.core.command.ProductCRUDCommand;
import lv.javaguru.ee.warehouse.core.command.ProductCommandResult;
import lv.javaguru.ee.warehouse.core.database.hibernate.DatabaseHibernateTest;
import lv.javaguru.ee.warehouse.core.domain.Product;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

/**
 *
 * @author dell
 */
public class ProductCRUDCommandHandlerTest extends DatabaseHibernateTest {
    
    @Autowired
    private CommandExecutor serviceExecutor;
    
    @Test
    public void testCommandCreate() {                
        ProductCRUDCommand command = getProductCRUDCommand(CREATE);        
        ProductCommandResult commandResult = serviceExecutor.execute(command);
        assertThat(commandResult, is(notNullValue()));
        assertThat(commandResult.getResult(), is(notNullValue()));
        assertThat(commandResult.getResult().getId(), is(notNullValue()));
    }
    
    @Test
    public void testCommandUpdate() {           
        final AtomicLong id = createDefaultProductInTrx(); 
        
        ProductCRUDCommand command = getProductCRUDCommand(UPDATE);        
        ProductCommandResult commandResult = serviceExecutor.execute(command);
        assertThat(commandResult, is(notNullValue()));
        assertThat(commandResult.getResult(), is(notNullValue()));
        assertThat(commandResult.getResult().getId(), is(id.get()));
        assertThat(commandResult.getResult().getDescription(), is(command.getDescription()));
        assertThat(commandResult.getResult().getTitle(), is(command.getTitle()));
    }
    
    @Test
    public void testCommandGet() {  
           
        final AtomicLong id = createDefaultProductInTrx();
                
        ProductCRUDCommand command = getProductCRUDCommand(GET);        
        ProductCommandResult commandResult = serviceExecutor.execute(command);
        assertThat(commandResult, is(notNullValue()));
        assertThat(commandResult.getResult(), is(notNullValue()));
        assertThat(commandResult.getResult().getId(), is(id.get()));
        assertThat(commandResult.getResult().getDescription(), is(nullValue()));
        assertThat(commandResult.getResult().getTitle(), is(nullValue()));
    }
        
    @Test
    public void testCommandDelete() {   
        
        final AtomicLong id = createDefaultProductInTrx();
                                
        ProductCRUDCommand command = getProductCRUDCommand(DELETE);        
        final ProductCommandResult commandResult = serviceExecutor.execute(command);
        assertThat(commandResult, is(notNullValue()));
        assertThat(commandResult.getResult(), is(notNullValue()));
        assertThat(commandResult.getResult().getId(), is(id.get()));
        assertThat(commandResult.getResult().getDescription(), is(nullValue()));
        assertThat(commandResult.getResult().getTitle(), is(nullValue()));
                  
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                Product deletedProduct = productDAO.getByCode(commandResult.getResult().getCode());
                assertThat(deletedProduct, is(nullValue()));
            }
        });
        
    }
    
    private ProductCRUDCommand getProductCRUDCommand(Action action) {
        ProductCRUDCommand command = new ProductCRUDCommand();
        command.setAction(action);
        command.setCode(getDefaultProduct().getCode());
        command.setDescription("description");
        command.setTitle("title");
        return command;
    }  
    
    private AtomicLong createDefaultProductInTrx() {    
        final AtomicLong id = new AtomicLong();
        
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {                
                Product product = createDefaultProduct();
                id.set(product.getId());
            }
        });
        return id;
    }
}
