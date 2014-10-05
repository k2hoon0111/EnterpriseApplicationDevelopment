package lv.javaguru.ee.bookshop.integrations.resourses;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by Viktor on 19/09/2014.
 */
public class CategoryResourceImpl implements CategoryResource {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private String baseWebServiceUrl;


    public CategoryResourceImpl(String baseWebServiceUrl) {
        this.baseWebServiceUrl = baseWebServiceUrl;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) throws RestException {
        try {
            ResponseEntity<CategoryDTO> responseEntity = REST_TEMPLATE.postForEntity(baseWebServiceUrl + CREATE_CATEGORY_URL,
                    categoryDTO, CategoryDTO.class, new HashMap<String, String>()
            );
            return responseEntity.getBody();
        } catch (Throwable e) {
            throw new RestException(e);
        }
    }

    @Override
    public CategoryDTO getCategory(Long categoryId) throws RestException {
        try {
            String getCategoryUrl = GET_CATEGORY_URL.replace("{categoryId}", categoryId.toString());
            ResponseEntity<CategoryDTO> responseEntity = REST_TEMPLATE.getForEntity(baseWebServiceUrl + getCategoryUrl,
                    CategoryDTO.class, new HashMap<String, String>()
            );
            return responseEntity.getBody();
        } catch (Throwable e) {
            throw new RestException(e);
        }
    }

    @Override
    public void updateCategory(Long categoryId, CategoryDTO categoryDTO) throws RestException {
        try {
            String updateCategoryUrl = UPDATE_CATEGORY_URL.replace("{categoryId}", categoryId.toString());
            REST_TEMPLATE.put(baseWebServiceUrl + updateCategoryUrl,
                    categoryDTO, CategoryDTO.class, new HashMap<String, String>()
            );
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RestException(e);
        }
    }

    @Override
    public void deleteCategory(Long categoryId) throws RestException {
        try {
            String deleteCategoryUrl = DELETE_CATEGORY_URL.replace("{categoryId}", categoryId.toString());
            REST_TEMPLATE.delete(baseWebServiceUrl + deleteCategoryUrl);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RestException(e);
        }
    }
}
