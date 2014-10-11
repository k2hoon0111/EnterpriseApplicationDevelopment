package lv.javaguru.ee.warehouse.integrations;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lv.javaguru.ee.warehouse.integrations.domain.OrderDTO;
import lv.javaguru.ee.warehouse.integrations.domain.ProductDTO;
import lv.javaguru.ee.warehouse.integrations.domain.WarehouseDTO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 * @author dell
 */
public class OrderStepDefinitions {
    
    private OrderDTO responseEntity;
    private RestException exception;
        
    private WarehouseDTO warehouseDTO;
    private ProductDTO productDTO;
    
    @Before(value = "@order")
    public void beforeScenario() {          
        this.warehouseDTO = RestFixture.createWarehouse(RestFixture.getDefaultWarehouse());                
        this.productDTO = RestFixture.createProduct(RestFixture.getDefaultProduct());        
    }
            
    @When("^the client requests incoming order resource$")
    public void client_requests_incoming_order() {        
        OrderDTO orderDTO = RestFixture.getDefaultOrder(warehouseDTO.getTitle(), productDTO.getCode());
        try {
            responseEntity = RestFixture.createIncomingOrder(orderDTO);            
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @Then("^the response must be valid from order resource$")
    public void response_must_be_valid() {
       assertThat(responseEntity, is(notNullValue()));
       assertThat(exception, is(nullValue()));    
    }
    
    @When("^the client requests outgoing order resource$")
    public void client_requests_outgoing_order() {        
        OrderDTO orderDTO = RestFixture.getDefaultOrder(warehouseDTO.getTitle(), productDTO.getCode());
        try {
            responseEntity = RestFixture.createOutgoingOrder(orderDTO);            
        } catch (RestException e) {
            this.exception = e;
        }
    }
    
}
