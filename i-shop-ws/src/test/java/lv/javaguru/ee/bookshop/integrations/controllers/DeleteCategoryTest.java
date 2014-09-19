package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

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
            assertEquals("Category id not valid", e.getResponseBodyAsString());
            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
        }

    }

}
