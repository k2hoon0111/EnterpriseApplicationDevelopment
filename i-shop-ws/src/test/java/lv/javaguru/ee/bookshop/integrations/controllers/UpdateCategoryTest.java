package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Created by nikoboro on 2014.09.19..
 */
public class UpdateCategoryTest extends EmbeddedJettyTest {

    @Test
    public void testUpdateCategory() {
        // Create category
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Test name");


        ResponseEntity<CategoryDTO> createCategoryResponse
                = RestFixture.createCategory(categoryDTO);
        CategoryDTO createdCategoryDTO = createCategoryResponse.getBody();

        MatcherAssert.assertThat(createdCategoryDTO.getCategoryId(), is(notNullValue()));
        MatcherAssert.assertThat(createdCategoryDTO.getName(), is("Test name"));

        // Update category
        CategoryDTO categoryDTOForUpdate = new CategoryDTO();
        categoryDTOForUpdate.setCategoryId(createdCategoryDTO.getCategoryId());
        categoryDTOForUpdate.setName("Test updated name");

        RestFixture.updateCategory(categoryDTOForUpdate);


        ResponseEntity<CategoryDTO> getCategoryResponse
                = RestFixture.getCategory(categoryDTOForUpdate.getCategoryId());

        CategoryDTO getCategoryDTO = getCategoryResponse.getBody();
        MatcherAssert.assertThat(getCategoryDTO.getCategoryId(), CoreMatchers.is(categoryDTOForUpdate.getCategoryId()));
        MatcherAssert.assertThat(getCategoryDTO.getName(), is("Test updated name"));
    }

}
