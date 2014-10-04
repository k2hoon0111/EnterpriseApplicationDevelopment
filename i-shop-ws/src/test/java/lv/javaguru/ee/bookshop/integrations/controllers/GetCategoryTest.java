package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

public class GetCategoryTest extends EmbeddedJettyTest {

    @Test
    public void testGetCategory() {
        CategoryDTO defaultCategoryDTO = DefaultObjectsFixture.createDefaultCategory();
        CategoryDTO createdCategoryDTO = RestFixture.createCategory(defaultCategoryDTO);

        Long categoryId = createdCategoryDTO.getCategoryId();

        CategoryDTO getCategoryDTO = RestFixture.getCategory(categoryId);
        MatcherAssert.assertThat(getCategoryDTO.getCategoryId(), is(categoryId));
        MatcherAssert.assertThat(getCategoryDTO.getName(), is(defaultCategoryDTO.getName()));
    }

    @Test
    public void testGetOrderWithWrongId() {
        try {
            RestFixture.getCategory(Long.MAX_VALUE);
        } catch (RestException e) {
            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getHttpStatus());
        }
    }
}
