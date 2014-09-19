package lv.javaguru.ee.bookshop.core.controllers;

import junit.framework.Assert;
import lv.javaguru.ee.bookshop.core.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.core.jetty.EmbeddedJettyTest;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Viktor on 16/09/2014.
 */
public class GetCategoryTest extends EmbeddedJettyTest {

    @Test
    public void testGetCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Test Name");

        ResponseEntity<CategoryDTO> createCategoryResponse
                = RestFixture.createCategory(categoryDTO);
        CategoryDTO createCategoryDTO = createCategoryResponse.getBody();

        Long categoryId = createCategoryDTO.getCategoryId();

        ResponseEntity<CategoryDTO> getCategoryResponse
                = RestFixture.getCategory(categoryId);

        CategoryDTO getCategoryDTO = getCategoryResponse.getBody();
        MatcherAssert.assertThat(getCategoryDTO.getCategoryId(), is(categoryId));
        MatcherAssert.assertThat(getCategoryDTO.getName(), is("Test Name"));

    }

    @Test
    public void testGetCategoryWithWrongId() {
        try {
            RestFixture.getCategory(Long.MAX_VALUE);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("Category id not valid", e.getResponseBodyAsString());
            Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
        }
    }

}
