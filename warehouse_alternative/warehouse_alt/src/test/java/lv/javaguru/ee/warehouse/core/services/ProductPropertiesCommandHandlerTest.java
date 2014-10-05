package lv.javaguru.ee.warehouse.core.services;

import lv.javaguru.ee.warehouse.core.command.ProductPropertiesCRUDCommand;
import lv.javaguru.ee.warehouse.core.database.ProductDAO;
import lv.javaguru.ee.warehouse.core.database.ProductPropertiesDAO;
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
public class ProductPropertiesCommandHandlerTest {
        
    @Mock
    private ProductPropertiesDAO prodPropDao;

    @Mock
    private ProductDAO productDao;
    
    @InjectMocks
    private ProductPropertiesCRUDCommandHandler handler = new ProductPropertiesCRUDCommandHandler();

    @Test
    public void testExecuteShouldThrowExceptionWhenCommandIsNull() {
        executeValidation(null, "ProductPropertiesCommand can not be empty");
    }

    @Test
    public void testExecuteShouldThrowExceptionWhenDeliveryIdIsNull() {        
        ProductPropertiesCRUDCommand command = new ProductPropertiesCRUDCommand();
        executeValidation(command, "ProductPropertiesCommand product id can not be empty");
    }

    private void executeValidation(ProductPropertiesCRUDCommand command, String errorMessage) {
        try {
            handler.execute(command);
            fail();
        } catch (Throwable e) {            
            assertThat(e.getMessage(), is(equalTo(errorMessage)));
        }
    }
    
}
