package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CategoryRESTTest extends EmbeddedJettyTest {

  @Test
  public void testCreateCategory() {

    CategoryDTO categoryDTO = DefaultObjectsFixture.createDefaultCategory();
    CategoryDTO createdCategoryDTO = RestFixture.createCategory(categoryDTO);

    MatcherAssert.assertThat(createdCategoryDTO.getCategoryId(), is(notNullValue()));
  }

  @Test
  public void testDeleteCategory() {
//    CategoryDTO categoryDTO = DefaultObjectsFixture.createDefaultCategory();
//    CategoryDTO createdCategoryDTO = RestFixture.createCategory(categoryDTO);
//
//    Long createdCategoryId = createdCategoryDTO.getCategoryId();
//
//    RestFixture.deleteCategory(createdCategoryId);

//    try {
//      RestFixture.getCategory(createdCategoryId);
//    } catch (RestException e) {
//      assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getHttpStatus());
//    }
  }


  @Test
  public void testGetCategory() {
//    CategoryDTO defaultCategoryDTO = DefaultObjectsFixture.createDefaultCategory();
//    CategoryDTO createdCategoryDTO = RestFixture.createCategory(defaultCategoryDTO);
//
//    Long categoryId = createdCategoryDTO.getCategoryId();
//
//    CategoryDTO getCategoryDTO = RestFixture.getCategory(categoryId);
//    MatcherAssert.assertThat(getCategoryDTO.getCategoryId(), is(categoryId));
//    MatcherAssert.assertThat(getCategoryDTO.getName(), is(defaultCategoryDTO.getName()));
  }

  @Test
  public void testGetOrderWithWrongId() {
//    try {
//      RestFixture.getCategory(Long.MAX_VALUE);
//    } catch (RestException e) {
//      Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getHttpStatus());
//    }
  }

  @Test
  public void testUpdateCategory() {
//    CategoryDTO categoryDTO = DefaultObjectsFixture.createDefaultCategory();
//    CategoryDTO createdCategoryDTO = RestFixture.createCategory(categoryDTO);
//
//    // Update category
//    CategoryDTO categoryDTOForUpdate = new CategoryDTO();
//    categoryDTOForUpdate.setCategoryId(createdCategoryDTO.getCategoryId());
//    categoryDTOForUpdate.setName("Test updated name");
//
//    RestFixture.updateCategory(createdCategoryDTO.getCategoryId(), categoryDTOForUpdate);
//
//    CategoryDTO updatedCategory = RestFixture.getCategory(createdCategoryDTO.getCategoryId());
//
//    MatcherAssert.assertThat(updatedCategory.getCategoryId(), CoreMatchers.is(categoryDTOForUpdate.getCategoryId()));
//    MatcherAssert.assertThat(updatedCategory.getName(), CoreMatchers.is("Test updated name"));
  }

}
