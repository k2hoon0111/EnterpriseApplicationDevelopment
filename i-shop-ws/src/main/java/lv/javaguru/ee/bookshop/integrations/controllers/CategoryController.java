package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.core.CommandExecutor;
import lv.javaguru.ee.bookshop.core.commands.*;
import lv.javaguru.ee.bookshop.core.domain.Category;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by MumboJumbo on 17/09/14.
 */

@Controller
public class CategoryController {

    @Autowired
    private CommandExecutor commandExecutor;

    @RequestMapping(method = RequestMethod.POST, value = "/rest/category/")
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

    @RequestMapping(method = RequestMethod.GET, value = "/rest/category/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long categoryId) {
        GetCategoryCommand command = new GetCategoryCommand(categoryId);
        GetCategoryResult result = commandExecutor.execute(command);
        Category category = result.getCategory();
        CategoryDTO categoryDTO = createCategoryDTO(category);
        return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rest/category/{categoryId}")
    public ResponseEntity<CategoryDTO> UpdateCategory(@RequestBody CategoryDTO categoryDTO) {
        UpdateCategoryCommand command = updateCategoryCommand(categoryDTO);
        UpdateCategoryResult result = commandExecutor.execute(command);

        return new ResponseEntity<CategoryDTO>(HttpStatus.OK);
    }

    private UpdateCategoryCommand updateCategoryCommand(CategoryDTO categoryDTO) {
        return new UpdateCategoryCommand(
                categoryDTO.getCategoryId(),
                categoryDTO.getName()
        );
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/rest/category/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Long categoryId) {
        DeleteCategoryCommand command = new DeleteCategoryCommand(categoryId);
        DeleteCategoryResult result = commandExecutor.execute(command);

        return new ResponseEntity(HttpStatus.OK);
    }

}
