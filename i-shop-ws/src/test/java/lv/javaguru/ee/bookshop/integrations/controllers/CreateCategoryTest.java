package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateCategoryTest extends EmbeddedJettyTest {

    @Test
    public void testCreateCategory() {

        CategoryDTO categoryDTO = DefaultObjectsFixture.createDefaultCategory();
        CategoryDTO createdCategoryDTO = RestFixture.createCategory(categoryDTO);

        MatcherAssert.assertThat(createdCategoryDTO.getCategoryId(), is(notNullValue()));
    }

}
