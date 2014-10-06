package lv.javaguru.ee.warehouse.core.services;

import java.util.concurrent.atomic.AtomicLong;
import lv.javaguru.ee.warehouse.core.CommandExecutor;
import lv.javaguru.ee.warehouse.core.command.Action;
import static lv.javaguru.ee.warehouse.core.command.Action.CREATE;
import static lv.javaguru.ee.warehouse.core.command.Action.DELETE;
import static lv.javaguru.ee.warehouse.core.command.Action.GET;
import static lv.javaguru.ee.warehouse.core.command.Action.UPDATE;
import lv.javaguru.ee.warehouse.core.command.ProductPropertiesCRUDCommand;
import lv.javaguru.ee.warehouse.core.command.ProductPropertiesCommandResult;
import lv.javaguru.ee.warehouse.core.database.hibernate.DatabaseHibernateTest;
import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.ProductProperties;
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
public class ProductPropertiesCRUDCommandHandlerTest extends DatabaseHibernateTest {

    @Autowired
    private CommandExecutor serviceExecutor;

    private Long defaultProductCode = getDefaultProduct().getCode();
    private String newProdPropValue = "value";
    private String oldProdPropValue = getDefaultProductProperties(null).getValue();
    
    @Test
    public void testCommandCreate() {        
        
        AtomicLong id = createDefaultProductPropertiesInTrx();        
        Product product = getProductByCode(defaultProductCode);
                
        ProductPropertiesCRUDCommand command = getProductPropertiesCRUDCommand(CREATE, product);
        
        ProductPropertiesCommandResult commandResult = serviceExecutor.execute(command);
        assertThat(commandResult, is(notNullValue()));
        assertThat(commandResult.getResult(), is(notNullValue()));
        assertThat(commandResult.getResult().getId(), is(notNullValue()));
    }

    @Test
    public void testCommandUpdate() {        
        AtomicLong id = createDefaultProductPropertiesInTrx();
        
        Product product = getProductByCode(defaultProductCode);
        ProductPropertiesCRUDCommand command = getProductPropertiesCRUDCommand(UPDATE, product);
        
        ProductPropertiesCommandResult commandResult = serviceExecutor.execute(command);
        assertThat(commandResult, is(notNullValue()));
        assertThat(commandResult.getResult(), is(notNullValue()));
        assertThat(commandResult.getResult().getId(), is(id.get()));
        assertThat(commandResult.getResult().getName(), is(command.getName()));
        assertThat(commandResult.getResult().getValue(), is(newProdPropValue));
    }

    @Test
    public void testCommandGet() {
        AtomicLong id = createDefaultProductPropertiesInTrx();
        
        Product product = getProductByCode(defaultProductCode);
        ProductPropertiesCRUDCommand command = getProductPropertiesCRUDCommand(GET, product);
                
        ProductPropertiesCommandResult commandResult = serviceExecutor.execute(command);
        assertThat(commandResult, is(notNullValue()));
        assertThat(commandResult.getResult(), is(notNullValue()));
        assertThat(commandResult.getResult().getId(), is(id.get()));
        assertThat(commandResult.getResult().getName(), is(command.getName()));
        assertThat(commandResult.getResult().getValue(), is(oldProdPropValue));
    }

    @Test
    public void testCommandDelete() {
        AtomicLong id = createDefaultProductPropertiesInTrx();
        
        final Product product = getProductByCode(defaultProductCode);
        final ProductPropertiesCRUDCommand command = getProductPropertiesCRUDCommand(DELETE, product);
        
        ProductPropertiesCommandResult commandResult = serviceExecutor.execute(command);
        assertThat(commandResult, is(notNullValue()));
        assertThat(commandResult.getResult(), is(notNullValue()));
        assertThat(commandResult.getResult().getId(), is(id.get()));
        assertThat(commandResult.getResult().getName(), is(command.getName()));
        assertThat(commandResult.getResult().getValue(), is(oldProdPropValue));

        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                ProductProperties deleted = productPropertiesDAO.getByProductAndPropertyName(product, command.getName());
                assertThat(deleted, is(nullValue()));
            }
        });
                
    }

    private ProductPropertiesCRUDCommand getProductPropertiesCRUDCommand(Action action, Product product) {
        ProductPropertiesCRUDCommand command = new ProductPropertiesCRUDCommand();
        command.setAction(action);
        command.setProduct(product);
        command.setName(getDefaultProductProperties(product).getName());
        command.setValue(newProdPropValue);
        return command;
    }
     
    private Product getProductByCode(final Long code) {    
        final Product[] products = new Product[1];        
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {                
                products[0] = productDAO.getByCode(code);                
            }
        });        
        return products[0];
    }
        
    private AtomicLong createDefaultProductPropertiesInTrx() {
        final AtomicLong id = new AtomicLong();        
        doInTransaction(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {                
                Product product = createDefaultProduct();
                ProductProperties prodProp = createDefaultProductProperties(product);
                id.set(prodProp.getId());
            }
        });        
        return id;
    }
    
}
