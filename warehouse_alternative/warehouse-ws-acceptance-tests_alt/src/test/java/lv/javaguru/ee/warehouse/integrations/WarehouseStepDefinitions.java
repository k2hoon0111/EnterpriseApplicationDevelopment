package lv.javaguru.ee.warehouse.integrations;

import cucumber.api.java.Before;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lv.javaguru.ee.warehouse.integrations.domain.WarehouseDTO;


public class WarehouseStepDefinitions {
    
    private WarehouseDTO responseEntity;
    private RestException exception;
    private final String updatedDescription = "updated description";
    
    private WarehouseDTO warehouseDTO;
    
    
    @Before(value = "@warehouse")
    public void beforeScenario() {          
        this.warehouseDTO = RestFixture.createWarehouse(RestFixture.getDefaultWarehouse());  
    }
    
    @When("^the client requests POST to warehouse resource$")
    public void client_requests_post() {        
        WarehouseDTO warehouseDTO_ = RestFixture.getDefaultWarehouse();
        try {
            responseEntity = RestFixture.createWarehouse(warehouseDTO_);            
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @Then("^the response must be valid from warehouse resource$")
    public void response_must_be_valid() {
        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getTitle(), is(notNullValue()));
    }
    
    @When("^the client requests GET to warehouse resource$")
    public void client_requests_get() {
        String warehouseCode = warehouseDTO.getTitle();
        try {
            responseEntity = RestFixture.getWarehouse(warehouseCode);
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @When("^the client requests GET with invalid id to warehouse resource$")
    public void client_requests_get_with_invalid_id() {        
        String warehouseCode = "zzzzzzzzzzzzzzzzz";
        try {
            responseEntity = RestFixture.getWarehouse(warehouseCode);
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @Then("^the response should be exception from warehouse resource$")
    public void response_should_be_exception() {
        assertThat(exception, is(notNullValue()));        
    }
    
    @Then("^the response exception status code should be \"(\\d*)\" from warehouse resource$")
    public void response_exception_status_code_should_be(Integer statusCode) {        
        assertThat(statusCode, is(exception.getHttpStatus()));
    }
        
    @When("^the client requests PUT to warehouse resource$")
    public void client_requests_update() {        
        String warehouseCode = warehouseDTO.getTitle();
        WarehouseDTO warehouse_ = RestFixture.getDefaultWarehouse();
        warehouse_.setTitle(warehouseCode);
        warehouse_.setDescription(updatedDescription);
        try {
            responseEntity = RestFixture.updateWarehouse(warehouseCode, warehouse_);
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @Then("^the response must contain updated fields from warehouse resource$")
    public void response_contains_updated_fields() {        
        assertThat(updatedDescription, is(responseEntity.getDescription()));
    }
    
    @When("^the client requests DELETE to warehouse resource$")
    public void client_requests_delete() {        
        String warehouseCode = warehouseDTO.getTitle();
        try {
            responseEntity = RestFixture.deleteWarehouse(warehouseCode);
        } catch (RestException e) {
            this.exception = e;
        }
    }
       
}
