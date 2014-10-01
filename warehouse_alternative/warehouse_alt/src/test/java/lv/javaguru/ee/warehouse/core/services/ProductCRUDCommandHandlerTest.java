package lv.javaguru.ee.warehouse.core.services;

import lv.javaguru.ee.warehouse.core.command.ProductCRUDCommand;
import lv.javaguru.ee.warehouse.core.command.ProductCommandResult;
import lv.javaguru.ee.warehouse.core.database.ProductDAO;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author dell
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductCRUDCommandHandlerTest {

    @Mock
    private ProductDAO deliveryDAO;

    @InjectMocks
    private ProductCRUDCommandHandler handler = new ProductCRUDCommandHandler();

    @Test
    public void testExecuteShouldThrowExceptionWhenCommandIsNull() {
        executeValidation(null, "ProductCommand can not be empty");
    }

    @Test
    public void testExecuteShouldThrowExceptionWhenDeliveryIdIsNull() {        
        ProductCRUDCommand command = new ProductCRUDCommand();
        executeValidation(command, "ProductCommand action can not be empty");
    }

    private void executeValidation(ProductCRUDCommand command, String errorMessage) {
        try {
            handler.execute(command);
            fail();
        } catch (Throwable e) {            
            assertThat(e.getMessage(), is(equalTo(errorMessage)));
        }
    }

}
