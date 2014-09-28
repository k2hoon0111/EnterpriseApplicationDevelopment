package lv.javaguru.ee.warehouse.core.services;

import lv.javaguru.ee.warehouse.core.command.WarehouseCRUDCommand;
import lv.javaguru.ee.warehouse.core.database.WarehouseDAO;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 *
 * @author dell
 */
public class WarehouseCRUDCommandHandlerTest {
    
    @Mock    
    private WarehouseDAO warehouseDao;

    @InjectMocks
    private WarehouseCRUDCommandHandler handler = new WarehouseCRUDCommandHandler();

    @Test
    public void testExecuteShouldThrowExceptionWhenCommandIsNull() {
        executeValidation(null, "WarehouseCommand can not be empty");
    }

    @Test
    public void testExecuteShouldThrowExceptionWhenDeliveryIdIsNull() {        
        WarehouseCRUDCommand command = new WarehouseCRUDCommand();
        executeValidation(command, "WarehouseCommand action can not be empty");
    }

    private void executeValidation(WarehouseCRUDCommand command, String errorMessage) {
        try {
            handler.execute(command);
            fail();
        } catch (Throwable e) {            
            assertThat(e.getMessage(), is(equalTo(errorMessage)));
        }
    }
    
}
