package lv.javaguru.ee.bookshop.integrations.resourses;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;

public interface CategoryResource {

  static final String CREATE_CATEGORY_URL = "/rest/category/";
  static final String CATEGORY_URL = "/rest/category/{categoryId}";

  CategoryDTO createCategory(CategoryDTO categoryDTO) throws RestException;

  CategoryDTO getCategory(Long categoryId) throws RestException;

  CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) throws RestException;

  CategoryDTO deleteCategory(Long categoryId) throws RestException;

}
