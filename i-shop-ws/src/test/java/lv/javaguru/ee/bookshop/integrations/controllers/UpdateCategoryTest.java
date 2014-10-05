package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Created by nikoboro on 2014.09.19..
 */
public class UpdateCategoryTest extends EmbeddedJettyTest {

    @Test
    public void testUpdateCategory() {
        CategoryDTO categoryDTO = DefaultObjectsFixture.createDefaultCategory();
        CategoryDTO createdCategoryDTO = RestFixture.createCategory(categoryDTO);

        // Update category
        CategoryDTO categoryDTOForUpdate = new CategoryDTO();
        categoryDTOForUpdate.setCategoryId(createdCategoryDTO.getCategoryId());
        categoryDTOForUpdate.setName("Test updated name");

        RestFixture.updateCategory(createdCategoryDTO.getCategoryId(), categoryDTOForUpdate);

        CategoryDTO updatedCategory = RestFixture.getCategory(createdCategoryDTO.getCategoryId());

        MatcherAssert.assertThat(updatedCategory.getCategoryId(), CoreMatchers.is(categoryDTOForUpdate.getCategoryId()));
        MatcherAssert.assertThat(updatedCategory.getName(), CoreMatchers.is("Test updated name"));
    }

}
