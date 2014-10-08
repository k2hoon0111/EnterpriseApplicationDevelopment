package lv.javaguru.ee.deliveryagency.integrations;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;
import lv.javaguru.ee.deliveryagency.integrations.resourses.DeliveryResource;
import lv.javaguru.ee.deliveryagency.integrations.resourses.DeliveryResourceImpl;


public class DeliveryStepDefinitions {

	private DeliveryResource deliveryResource = createDeliveryResource();
	
	private DeliveryDTO responseEntity;
	private RestException exception;


    private static DeliveryResource createDeliveryResource() {
        PropertiesReader propertiesReader = new PropertiesReader();
        String baseUrl = propertiesReader.getBaseUrl();
        return new DeliveryResourceImpl(baseUrl);
    }

	@When("^the client requests POST$")
	public void client_requests_post() {
		DeliveryDTO deliveryDTO = new DeliveryDTO();
        try {
            responseEntity = deliveryResource.createDelivery(deliveryDTO);
        } catch (RestException e) {
            this.exception = e;
        }
	}

	@When("^the client requests GET$")
	public void client_requests_get() {
		Long deliveryId = responseEntity.getDeliveryId();
        try {
            responseEntity = deliveryResource.getDelivery(deliveryId);
        } catch (RestException e) {
            this.exception = e;
        }
	}

    @When("^the client requests GET with invalid id$")
    public void client_requests_get_with_invalid_id() {
        Long deliveryId = Long.MAX_VALUE;
        try {
            responseEntity = deliveryResource.getDelivery(deliveryId);
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @Then("^the response exception status code should be \"([^\"]*)\"$")
    public void response_exception_status_code_should_be(String responseStatus) {
        assertThat(Integer.parseInt(responseStatus), is(exception.getHttpStatus()));
    }

    @Then("^the response should be exception")
    public void response_should_be_exception() {
        assertThat(exception, is(notNullValue()));
    }

    @Then("^the response must be valid")
	public void response_must_be_valid() {
        assertThat(responseEntity, is(notNullValue()));
		assertThat(responseEntity.getDeliveryId(), is(notNullValue()));
	}
	
}
