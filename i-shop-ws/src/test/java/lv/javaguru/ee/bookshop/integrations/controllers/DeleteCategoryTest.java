package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by MumboJumbo on 19/09/14.
 */

public class DeleteCategoryTest extends EmbeddedJettyTest {

    @Test
    public void testDeleteCategory() {
        CategoryDTO categoryDTO = DefaultObjectsFixture.createDefaultCategory();
        CategoryDTO createdCategoryDTO = RestFixture.createCategory(categoryDTO);

        Long createdCategoryId = createdCategoryDTO.getCategoryId();

        RestFixture.deleteCategory(createdCategoryId);

        try {
            RestFixture.getCategory(createdCategoryId);
        } catch (RestException e) {
            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getHttpStatus());
        }

    }

}
