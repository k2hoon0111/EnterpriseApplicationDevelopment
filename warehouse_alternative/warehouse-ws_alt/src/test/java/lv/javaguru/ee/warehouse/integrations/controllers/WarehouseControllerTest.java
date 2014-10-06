package lv.javaguru.ee.warehouse.integrations.controllers;

import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.WarehouseDTO;
import lv.javaguru.ee.warehouse.integrations.jetty.EmbeddedJettyTest;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.HttpStatus;

/**
 *
 * @author dell
 */
public class WarehouseControllerTest extends EmbeddedJettyTest {
        
    @Test
    public void getWarehouseTest() {        
        String warehouseCode = "WH-2222";
        WarehouseDTO warehouseDTO = createWarehouseWithCode(warehouseCode);
           
        WarehouseDTO warehouse = RestFixture.getWarehouse(warehouseCode);
        assertNotNull(warehouseDTO);
        assertThat(warehouse.getTitle(), is(warehouseCode));
    }
    
    @Test
    public void createWarehouseTest() {
        String warehouseCode = "WH-3333";
        WarehouseDTO warehouseDTO = createWarehouseWithCode(warehouseCode);        
        assertThat(warehouseDTO.getTitle(), is(warehouseCode));
    }
    
    @Test
    public void updateWarehouseTest() {
        String warehouseCode = "WH-4444";
        WarehouseDTO warehouseDTO = createWarehouseWithCode(warehouseCode);
       
        String newDescription = "updated description";
        warehouseDTO.setDescription(newDescription);
        
        WarehouseDTO warehouse = RestFixture.updateWarehouse(warehouseCode, warehouseDTO);
        assertNotNull(warehouse);
        assertThat(warehouse.getTitle(), is(warehouseCode));
        assertThat(warehouse.getDescription(), is(newDescription));
    }
    
    @Test
    public void deleteWarehouseTest() {
        String warehouseCode = "WH-5555";
        WarehouseDTO warehouseDTO = createWarehouseWithCode(warehouseCode);
        
        WarehouseDTO warehouse = RestFixture.deleteWarehouse(warehouseCode);
        assertNotNull(warehouse);
        assertThat(warehouse.getTitle(), is(warehouseCode));
    }
        
    @Test
    public void getWarehouseWithWrongIdTest() {        
        try {
            RestFixture.getWarehouse("zzzzzzzzzzzzzzzzzzzzzz");
        } catch (RestException e) {
            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getHttpStatus());
        }
    }
    
    
    private WarehouseDTO createWarehouseWithCode(String warehouseCode) {        
        WarehouseDTO warehouseDTO = RestFixture.createWarehouse(getProductWithCode(warehouseCode));
        assertNotNull(warehouseDTO);
        return warehouseDTO;
    }
    
    private WarehouseDTO getProductWithCode(String warehouseCode) {    
        WarehouseDTO warehouseDTO = RestFixture.getDefaultWarehouse();
        warehouseDTO.setTitle(warehouseCode);
        return warehouseDTO;
    } 
}
