package lv.javaguru.ee.warehouse.integrations;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lv.javaguru.ee.warehouse.integrations.domain.ProductDTO;
import lv.javaguru.ee.warehouse.integrations.domain.ProductPropertiesDTO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 *
 * @author dell
 */
public class ProductPropertiesStepDefinitions {
    
    private ProductPropertiesDTO responseEntity;
    private RestException exception;
    
    private static final ProductDTO productDTO = RestFixture.createProduct(RestFixture.getDefaultProduct());
    private ProductPropertiesDTO prodPropsDTO;
    
    private final String updatedValue = "updated value";
    
    
    @Before(value = "@productproperties")
    public void beforeScenario() {
        this.prodPropsDTO = RestFixture.createProdProps(productDTO.getCode(), RestFixture.getDefaultProdProps());    
    }
    
    @When("^the client requests POST to product properties resource$")
    public void client_requests_post() {        
        ProductPropertiesDTO prodPropsDTO_ = RestFixture.getDefaultProdProps();
        try {
            responseEntity = RestFixture.createProdProps(productDTO.getCode(), prodPropsDTO_);            
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @Then("^the response must be valid from product properties resource$")
    public void response_must_be_valid() {
        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getProductCode(), is(productDTO.getCode()));
    }
    
    @When("^the client requests GET to product properties resource$")
    public void client_requests_get() {        
        Long productCode = productDTO.getCode();
        String propertyName = prodPropsDTO.getName();
        try {
            responseEntity = RestFixture.getProdProps(productCode, propertyName);
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @When("^the client requests GET with invalid id to product properties resource$")
    public void client_requests_get_with_invalid_id() {  
        Long productCode = productDTO.getCode();
        String propertyName = "zzzzzzzzzzzzzzzzz";
        try {
            responseEntity = RestFixture.getProdProps(productCode, propertyName);
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @Then("^the response should be exception from product properties resource$")
    public void response_should_be_exception() {
        assertThat(exception, is(notNullValue()));        
    }
    
    @Then("^the response exception status code should be \"(\\d*)\" from product properties resource$")
    public void response_exception_status_code_should_be(Integer statusCode) {
        assertThat(statusCode, is(exception.getHttpStatus()));
    }
        
    @When("^the client requests PUT to product properties resource$")
    public void client_requests_update() {         
        Long productCode = productDTO.getCode();
        String propertyName = prodPropsDTO.getName();
        try {
            responseEntity = RestFixture.updateProdProps(productCode, propertyName, updatedValue);
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @Then("^the response must contain updated fields from product properties resource$")
    public void response_contains_updated_fields() {        
        assertThat(updatedValue, is(responseEntity.getValue()));
    }
    
    @When("^the client requests DELETE to product properties resource$")
    public void client_requests_delete() {          
        Long productCode = productDTO.getCode();
        String propertyName = prodPropsDTO.getName();                
        try {
            responseEntity = RestFixture.deleteProdProps(productCode, propertyName);
        } catch (RestException e) {
            this.exception = e;
        }
    }
    
    @When("^the client requests GET to deleted product properties resource$")
    public void client_request_deleted_product_propertie() {    
        Long productCode = productDTO.getCode();
        String propertyName = responseEntity.getName();
        try {
            responseEntity = RestFixture.getProdProps(productCode, propertyName);
        } catch (RestException e) {
            this.exception = e;
        }        
    }
    
}
