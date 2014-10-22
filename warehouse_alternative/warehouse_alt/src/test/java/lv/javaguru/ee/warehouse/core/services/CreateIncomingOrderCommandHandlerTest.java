package lv.javaguru.ee.warehouse.core.services;

import lv.javaguru.ee.warehouse.core.command.CreateIncomingOrderCommand;
import lv.javaguru.ee.warehouse.core.database.OrderDAO;
import lv.javaguru.ee.warehouse.core.database.ProductDAO;
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
public class CreateIncomingOrderCommandHandlerTest {
    
    @Mock    
    private OrderDAO orderDAO;
    
    @Mock  
    private ProductDAO productDAO;
    
    @Mock  
    private WarehouseDAO warehouseDAO;

    @InjectMocks
    private CreateIncomingOrderCommandHandler handler = new CreateIncomingOrderCommandHandler();

    @Test
    public void testExecuteShouldThrowExceptionWhenCommandIsNull() {
        executeValidation(null, "IncomingOrder can not be empty");
    }

    @Test
    public void testExecuteShouldThrowExceptionWhenDeliveryIdIsNull() {        
        CreateIncomingOrderCommand command = new CreateIncomingOrderCommand();
        executeValidation(command, "IncomingOrder products can not be empty");
    }

    private void executeValidation(CreateIncomingOrderCommand command, String errorMessage) {
        try {
            handler.execute(command);
            fail();
        } catch (Throwable e) {            
            assertThat(e.getMessage(), is(equalTo(errorMessage)));
        }
    }
    
}
