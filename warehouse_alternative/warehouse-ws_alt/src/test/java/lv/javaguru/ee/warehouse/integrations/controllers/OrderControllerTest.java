package lv.javaguru.ee.warehouse.integrations.controllers;

import lv.javaguru.ee.warehouse.integrations.domain.OrderDTO;
import lv.javaguru.ee.warehouse.integrations.domain.ProductDTO;
import lv.javaguru.ee.warehouse.integrations.domain.WarehouseDTO;
import lv.javaguru.ee.warehouse.integrations.jetty.EmbeddedJettyTest;
import static org.hamcrest.CoreMatchers.is;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author dell
 */
//@Ignore
public class OrderControllerTest extends EmbeddedJettyTest {
    
    private static WarehouseDTO warehouseDTO;
    private static ProductDTO productDTO;
    
    @BeforeClass
    public static void initTest() {        
        WarehouseDTO warehouse = RestFixture.getDefaultWarehouse();
        warehouse.setTitle("WH-222");
        warehouseDTO = RestFixture.createWarehouse(warehouse);
        ProductDTO product = RestFixture.getDefaultProduct();
        product.setCode(5599L);
        productDTO = RestFixture.createProduct(RestFixture.getDefaultProduct());
    }
    
    @AfterClass
    public static void cleanTest() {
        warehouseDTO = null;
        productDTO = null;
    }
    
    @Test
    public void createIncomingOrder() {        
        OrderDTO orderDTO = getDefaultOrderDTO();        
        OrderDTO order = RestFixture.createIncomingOrder(orderDTO);
        assertNotNull(order);
        assertThat(order.getWarehouseCode(), is(orderDTO.getWarehouseCode()));
        assertThat(order.getProductCodess().size(), is(1));
        assertTrue(order.getProductCodess().containsAll(orderDTO.getProductCodess()));
    }
    
    @Test
    public void createOutgoingOrder() {        
        OrderDTO orderDTO = getDefaultOrderDTO();                
        OrderDTO order = RestFixture.createIncomingOrder(orderDTO);
        assertNotNull(order);
        
        RestFixture.createOutgoingOrder(orderDTO);
        assertNotNull(order);
        assertThat(order.getWarehouseCode(), is(orderDTO.getWarehouseCode()));
        assertThat(order.getProductCodess().size(), is(1));
        assertTrue(order.getProductCodess().containsAll(orderDTO.getProductCodess()));
    }
    
    private OrderDTO getDefaultOrderDTO() {    
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setWarehouseCode(warehouseDTO.getTitle());
        orderDTO.addProductCode(productDTO.getCode());
        orderDTO.setAmount(10);
        orderDTO.setQuantity(1);
        return orderDTO;
    }
    
}
