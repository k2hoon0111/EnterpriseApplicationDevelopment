package lv.javaguru.ee.bookshop.core.controllers;

import junit.framework.TestCase;
import lv.javaguru.ee.bookshop.core.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.core.domain.Category;
import lv.javaguru.ee.bookshop.core.jetty.EmbeddedJettyTest;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by MumboJumbo on 19/09/14.
 */

public class DeleteCategoryTest extends EmbeddedJettyTest {

    @Test
    public void testDeleteCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Test title");


        ResponseEntity<CategoryDTO> createCategoryResponse
                = RestFixture.createCategory(categoryDTO);
        CategoryDTO createdCategoryDTO = createCategoryResponse.getBody();

        Long categoryId = createdCategoryDTO.getCategoryId();

        RestFixture.deleteCategory(categoryId);


        try {
            RestFixture.getCategory(Long.valueOf(categoryId));
        } catch (HttpClientErrorException e) {
            TestCase.assertEquals("Entity " + Category.class.getName() + " not found by id " + categoryId, e.getResponseBodyAsString());
            TestCase.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
        }

    }

}
