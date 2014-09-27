package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetCategoryTest extends EmbeddedJettyTest {

    @Test
    public void testGetCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("C++");
        CategoryDTO createdCategoryDTO = RestFixture.createCategory(categoryDTO);
        MatcherAssert.assertThat(createdCategoryDTO.getCategoryId(), is(notNullValue()));

        Long categoryId = createdCategoryDTO.getCategoryId();

        CategoryDTO getCategoryDTO = RestFixture.getCategory(categoryId);
        MatcherAssert.assertThat(getCategoryDTO.getCategoryId(), is(categoryId));
        MatcherAssert.assertThat(getCategoryDTO.getName(), is("C++"));

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
