package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.core.CommandExecutor;
import lv.javaguru.ee.bookshop.core.commands.*;
import lv.javaguru.ee.bookshop.core.domain.Category;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;

/**
 * Created by MumboJumbo on 17/09/14.
 */

@RestController
@RequestMapping("/rest/category")
public class CategoryController {

  @Autowired
  private CommandExecutor commandExecutor;

  @RequestMapping(method = RequestMethod.POST)
  @Produces("application/json")
  public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
    CreateCategoryCommand command = createCategoryCommand(categoryDTO);
    CreateCategoryResult result = commandExecutor.execute(command);

    Category category = result.getCategory();
    CategoryDTO resultDTO = createCategoryDTO(category);

    return new ResponseEntity<CategoryDTO>(resultDTO, HttpStatus.CREATED);
  }

  private CategoryDTO createCategoryDTO(Category category) {
    CategoryDTO categoryDTO = new CategoryDTO();
    categoryDTO.setName(category.getName());
    categoryDTO.setCategoryId(category.getCategoryId());
    return categoryDTO;
  }

  private CreateCategoryCommand createCategoryCommand(CategoryDTO categoryDTO) {
    return new CreateCategoryCommand(
        categoryDTO.getName()
    );
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{categoryId}")
  @Produces("application/json")
  public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long categoryId) {
    GetCategoryCommand command = new GetCategoryCommand(categoryId);
    GetCategoryResult result = commandExecutor.execute(command);
    Category category = result.getCategory();

    if (category == null) {
      return new ResponseEntity<CategoryDTO>(HttpStatus.NOT_FOUND);
    }

    CategoryDTO clientDTO = createCategoryDTO(category);
    return new ResponseEntity<CategoryDTO>(clientDTO, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{categoryId}")
  @Produces("application/json")
  public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId,
                                                    @RequestBody CategoryDTO categoryDTO) {
    UpdateCategoryCommand command = updateCategoryCommand(categoryDTO);
    UpdateCategoryResult result = commandExecutor.execute(command);

    Category category = result.getCategory();
    CategoryDTO updatedCategoryDTO = createCategoryDTO(category);

    return new ResponseEntity<CategoryDTO>(updatedCategoryDTO, HttpStatus.OK);

  }

  private UpdateCategoryCommand updateCategoryCommand(CategoryDTO categoryDTO) {
    return new UpdateCategoryCommand(
        categoryDTO.getCategoryId(),
        categoryDTO.getName()
    );
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{categoryId}")
  @Produces("application/json")
  public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
    DeleteCategoryCommand command = new DeleteCategoryCommand(categoryId);
    DeleteCategoryResult result = commandExecutor.execute(command);
    Category deletedCategory = result.getCategory();

    if (deletedCategory == null) {
      return new ResponseEntity<CategoryDTO>(HttpStatus.NOT_FOUND);
    }

    CategoryDTO clientDTO = createCategoryDTO(deletedCategory);
    return new ResponseEntity<CategoryDTO>(clientDTO, HttpStatus.OK);
  }

}
