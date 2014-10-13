package lv.javaguru.ee.bookshop.integrations.resourses;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Created by Viktor on 19/09/2014.
 */
public class CategoryResourceImpl implements CategoryResource {

  private String baseWebServiceUrl;
  private Client client;


  public CategoryResourceImpl(String baseWebServiceUrl) {
    this.baseWebServiceUrl = baseWebServiceUrl;
    this.client = ClientBuilder.newClient();
  }

  @Override
  public CategoryDTO createCategory(CategoryDTO categoryDTO) throws RestException {
    try {
      String categoryUrl = baseWebServiceUrl + CREATE_CATEGORY_URL;
      WebTarget target = client.target(categoryUrl);
      return target.request(MediaType.APPLICATION_XML)
          .post(Entity.entity(categoryDTO, MediaType.APPLICATION_XML), CategoryDTO.class);
    } catch (Throwable e) {
      throw new RestException(e);
    }
  }

  @Override
  public CategoryDTO getCategory(Long categoryId) throws RestException {
    try {
      String getCategoryUrl = baseWebServiceUrl + CATEGORY_URL.replace("{categoryId}", categoryId.toString());
      WebTarget target = client.target(getCategoryUrl);
      return target.request(MediaType.APPLICATION_XML).get(CategoryDTO.class);
    } catch (Throwable e) {
      throw new RestException(e);
    }
  }

  @Override
  public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) throws RestException {
    try {
      String updateCategoryUrl = baseWebServiceUrl + CATEGORY_URL.replace("{categoryId}", categoryId.toString());
      WebTarget target = client.target(updateCategoryUrl);
      return target.request(MediaType.APPLICATION_XML)
          .put(Entity.entity(categoryDTO, MediaType.APPLICATION_XML), CategoryDTO.class);
    } catch (Throwable e) {
      throw new RestException(e);
    }
  }

  @Override
  public CategoryDTO deleteCategory(Long categoryId) throws RestException {
    try {
      String deleteCategoryUrl = baseWebServiceUrl + CATEGORY_URL.replace("{categoryId}", categoryId.toString());
      WebTarget target = client.target(deleteCategoryUrl);
      return target.request(MediaType.APPLICATION_XML).delete(CategoryDTO.class);
    } catch (Throwable e) {
      throw new RestException(e);
    }
  }
}
