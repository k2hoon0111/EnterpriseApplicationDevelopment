package lv.javaguru.ee.warehouse.integrations;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lv.javaguru.ee.warehouse.integrations.domain.ProductDTO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.springframework.http.HttpStatus;

/**
 *
 * @author dell
 */
public class ProductStepDefinitions {
    
    private ProductDTO responseEntity;
    private RestException exception;
    private final String updatedDescription = "updated description";
        
    private ProductDTO productDTO;
        
    
    @Before(value = "@product")
    public void beforeScenario() {          
        this.productDTO = RestFixture.createProduct(RestFixture.getDefaultProduct());  
    }
    
    @When("^the client requests POST to product resource$")
    public void client_requests_post() {        
        ProductDTO productDTO_ = RestFixture.getDefaultProduct();
        try {
            responseEntity = RestFixture.createProduct(productDTO_);            
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @Then("^the response must be valid from product resource")
    public void response_must_be_valid() {
        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getTitle(), is(notNullValue()));
    }
    
    @When("^the client requests GET to product resource$")
    public void client_requests_get() {
        Long productCode = productDTO.getCode();
        try {
            responseEntity = RestFixture.getProduct(productCode);
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @When("^the client requests GET with invalid id to product resource$")
    public void client_requests_get_with_invalid_id() {        
        Long productCode = Long.MAX_VALUE;
        try {
            responseEntity = RestFixture.getProduct(productCode);
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @Then("^the response should be exception from product resource")
    public void response_should_be_exception() {
        assertThat(exception, is(notNullValue()));        
    }
    
    @Then("^the response exception status code should be \"(\\d*)\" from product resource$")
    public void response_exception_status_code_should_be(Integer responseStatus) {        
        HttpStatus statusCode = HttpStatus.valueOf(responseStatus);
        assertThat(statusCode, is(exception.getHttpStatus()));
    }
        
    @When("^the client requests PUT to product resource$")
    public void client_requests_update() {        
        Long productCode = productDTO.getCode();
        ProductDTO productDTO_ = RestFixture.getDefaultProduct();
        productDTO_.setCode(productCode);
        productDTO_.setDescription(updatedDescription);
        try {
            responseEntity = RestFixture.updateProduct(productCode, productDTO_);
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @Then("^the response must contain updated fields from product resource$")
    public void response_contains_updated_fields() {        
        assertThat(updatedDescription, is(responseEntity.getDescription()));
    }
    
    @When("^the client requests DELETE to product resource$")
    public void client_requests_delete() {        
        Long productCode = productDTO.getCode();
        try {
            responseEntity = RestFixture.deleteProduct(productCode);
        } catch (RestException e) {
            this.exception = e;
        }
    }
    
}
