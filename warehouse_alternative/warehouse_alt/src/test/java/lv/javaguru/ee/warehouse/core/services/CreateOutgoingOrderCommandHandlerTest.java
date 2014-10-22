/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.javaguru.ee.warehouse.core.services;

import lv.javaguru.ee.warehouse.core.command.CreateOutgoingOrderCommand;
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
public class CreateOutgoingOrderCommandHandlerTest {
    
    @Mock    
    private OrderDAO orderDAO;
    
    @Mock  
    private ProductDAO productDAO;
    
    @Mock  
    private WarehouseDAO warehouseDAO;

    @InjectMocks
    private CreateOutgoingOrderCommandHandler handler = new CreateOutgoingOrderCommandHandler();

    @Test
    public void testExecuteShouldThrowExceptionWhenCommandIsNull() {
        executeValidation(null, "OutgoingOrder can not be empty");
    }

    @Test
    public void testExecuteShouldThrowExceptionWhenDeliveryIdIsNull() {        
        CreateOutgoingOrderCommand command = new CreateOutgoingOrderCommand();
        executeValidation(command, "OutgoingOrder products can not be empty");
    }

    private void executeValidation(CreateOutgoingOrderCommand command, String errorMessage) {
        try {
            handler.execute(command);
            fail();
        } catch (Throwable e) {            
            assertThat(e.getMessage(), is(equalTo(errorMessage)));
        }
    }
    
}
