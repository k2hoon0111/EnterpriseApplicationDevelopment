package lv.javaguru.ee.bookshop.integrations;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.resourses.CategoryResource;
import lv.javaguru.ee.bookshop.integrations.resourses.CategoryResourceImpl;
import org.springframework.http.HttpStatus;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


public class CategoryStepDefinitions {

    private CategoryResource categoryResource = createCategoryResource();

    private CategoryDTO responseEntity;
    private RestException exception;


    private static CategoryResource createCategoryResource() {
        PropertiesReader propertiesReader = new PropertiesReader();
        String baseUrl = propertiesReader.getBaseUrl();
        return new CategoryResourceImpl(baseUrl);
    }

    @When("^the client requests POST$")
    public void client_requests_post() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("C++");

        try {
            responseEntity = categoryResource.createCategory(categoryDTO);
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @When("^the client requests GET$")
    public void client_requests_get() {
        Long categoryId = responseEntity.getCategoryId();
        try {
            responseEntity = categoryResource.getCategory(categoryId);
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @When("^the client requests GET with invalid id$")
    public void client_requests_get_with_invalid_id() {
        Long categoryId = Long.MAX_VALUE;
        try {
            responseEntity = categoryResource.getCategory(categoryId);
        } catch (RestException e) {
            this.exception = e;
        }
    }

    @Then("^the response exception status code should be \"([^\"]*)\"$")
    public void response_exception_status_code_should_be(String responseStatus) {
        HttpStatus statusCode = HttpStatus.valueOf(responseStatus);
        assertThat(statusCode, is(exception.getHttpStatus()));
    }

    @Then("^the response should be exception")
    public void response_should_be_exception() {
        assertThat(exception, is(notNullValue()));
    }

    @Then("^the response must be valid")
    public void response_must_be_valid() {
        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getCategoryId(), is(notNullValue()));
    }

}
