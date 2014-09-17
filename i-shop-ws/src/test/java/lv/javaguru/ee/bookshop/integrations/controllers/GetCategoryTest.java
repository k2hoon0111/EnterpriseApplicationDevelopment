package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
        assertThat(getCategoryDTO.getCategoryId(), is(categoryId));
        assertThat(getCategoryDTO.getName(), is("Test Name"));

    }

    @Test
    public void testGetCategoryWithWrongId() {
        try {
            RestFixture.getCategory(Long.MAX_VALUE);
        } catch (HttpClientErrorException e) {
            assertEquals("Category id not valid", e.getResponseBodyAsString());
            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
        }
    }

}
