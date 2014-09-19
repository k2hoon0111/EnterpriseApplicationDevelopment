package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

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

    assertThat(createdCategoryDTO.getCategoryId(), is(notNullValue()));
    assertThat(createdCategoryDTO.getName(), is("Test name"));

    // Update category
    CategoryDTO categoryDTOForUpdate = new CategoryDTO();
    categoryDTOForUpdate.setCategoryId(createdCategoryDTO.getCategoryId());
    categoryDTOForUpdate.setName("Test updated name");

    RestFixture.updateCategory(categoryDTOForUpdate);


    ResponseEntity<CategoryDTO> getCategoryResponse
        = RestFixture.getCategory(categoryDTOForUpdate.getCategoryId());

    CategoryDTO getCategoryDTO = getCategoryResponse.getBody();
    assertThat(getCategoryDTO.getCategoryId(), is(categoryDTOForUpdate.getCategoryId()));
    assertThat(getCategoryDTO.getName(), is("Test updated name"));
  }

}
