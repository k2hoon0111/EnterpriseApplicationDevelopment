package lv.javaguru.ee.bookshop.integrations.resourses;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;

public interface CategoryResource {

	static final String CREATE_CATEGORY_URL = "/rest/category/";
	static final String GET_CATEGORY_URL = "/rest/category/{categoryId}";
    static final String UPDATE_CATEGORY_URL = "/rest/category/{categoryId}";
    static final String DELETE_CATEGORY_URL = "/rest/category/{categoryId}";


	CategoryDTO createCategory(CategoryDTO categoryDTO) throws RestException;

	CategoryDTO getCategory(Long categoryId) throws RestException;

    void updateCategory(Long categoryId, CategoryDTO categoryDTO) throws RestException;

    void deleteCategory(Long categoryId) throws RestException;

}
