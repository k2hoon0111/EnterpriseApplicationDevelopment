package lv.javaguru.ee.warehouse.core.services;

import java.util.concurrent.atomic.AtomicLong;
import lv.javaguru.ee.warehouse.core.CommandExecutor;
import lv.javaguru.ee.warehouse.core.command.Action;
import static lv.javaguru.ee.warehouse.core.command.Action.*;
import lv.javaguru.ee.warehouse.core.command.WarehouseCRUDCommand;
import lv.javaguru.ee.warehouse.core.command.WarehouseCommandResult;
import lv.javaguru.ee.warehouse.core.database.hibernate.DatabaseHibernateTest;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;
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
public class WarehouseCRUDCommandHandlerTest extends DatabaseHibernateTest {
    
    @Autowired
    private CommandExecutor serviceExecutor;

    @Test
    public void testCommandCreate() {                
        WarehouseCRUDCommand command = getWarehouseCRUDCommand(CREATE);        
        WarehouseCommandResult commandResult = serviceExecutor.execute(command);
        assertThat(commandResult, is(notNullValue()));
        assertThat(commandResult.getResult(), is(notNullValue()));
        assertThat(commandResult.getResult().getId(), is(notNullValue()));
    }

    @Test
    public void testCommandUpdate() {   
        AtomicLong id = createDefaultWarehouseInTrx();
        
        WarehouseCRUDCommand command = getWarehouseCRUDCommand(UPDATE);        
        WarehouseCommandResult commandResult = serviceExecutor.execute(command);
        assertThat(commandResult, is(notNullValue()));
        assertThat(commandResult.getResult(), is(notNullValue()));
        assertThat(commandResult.getResult().getId(), is(id.get()));
        assertThat(commandResult.getResult().getDescription(), is(command.getDescription()));
        assertThat(commandResult.getResult().getTitle(), is(command.getTitle()));
    }
    
    @Test
    public void testCommandGet() {   
        AtomicLong id = createDefaultWarehouseInTrx();
                
        WarehouseCRUDCommand command = getWarehouseCRUDCommand(GET);        
        WarehouseCommandResult commandResult = serviceExecutor.execute(command);
        assertThat(commandResult, is(notNullValue()));
        assertThat(commandResult.getResult(), is(notNullValue()));
        assertThat(commandResult.getResult().getId(), is(id.get()));
        assertThat(commandResult.getResult().getDescription(), is(nullValue()));
        assertThat(commandResult.getResult().getTitle(), is(notNullValue()));
    }
    
    @Test
    public void testCommandDelete() {   
        AtomicLong id = createDefaultWarehouseInTrx();
               
        WarehouseCRUDCommand command = getWarehouseCRUDCommand(DELETE);        
        final WarehouseCommandResult commandResult = serviceExecutor.execute(command);
        assertThat(commandResult, is(notNullValue()));
        assertThat(commandResult.getResult(), is(notNullValue()));
        assertThat(commandResult.getResult().getId(), is(id.get()));
        assertThat(commandResult.getResult().getDescription(), is(nullValue()));
        assertThat(commandResult.getResult().getTitle(), is(notNullValue()));
        
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                Warehouse deleted = warehouseDAO.getByTitle(commandResult.getResult().getTitle());
                assertThat(deleted, is(nullValue())); 
            }
        });    
    }
    
    private WarehouseCRUDCommand getWarehouseCRUDCommand(Action action) {
        WarehouseCRUDCommand command = new WarehouseCRUDCommand();
        command.setAction(action);        
        command.setDescription("description");
        command.setTitle(getDefaultWarehouse().getTitle());
        return command;
    } 
    
    private AtomicLong createDefaultWarehouseInTrx() {    
        final AtomicLong id = new AtomicLong();        
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                Warehouse warehouse = createDefaultWarehouse();
                id.set(warehouse.getId());
            }
        });        
        return id;
    }
            
}
