package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.core.CommandExecutor;
import lv.javaguru.ee.bookshop.core.commands.*;
import lv.javaguru.ee.bookshop.core.domain.Category;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.resourses.CategoryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by MumboJumbo on 17/09/14.
 */

@RestController
public class CategoryController implements CategoryResource {

    @Autowired
    private CommandExecutor commandExecutor;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/rest/category/")
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        CreateCategoryCommand command = createCategoryCommand(categoryDTO);
        CreateCategoryResult result = commandExecutor.execute(command);

        Category category = result.getCategory();
        CategoryDTO resultDTO = createCategoryDTO(category);

        return resultDTO;
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

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/rest/category/{categoryId}")
    public CategoryDTO getCategory(@PathVariable Long categoryId) {
        GetCategoryCommand command = new GetCategoryCommand(categoryId);
        GetCategoryResult result = commandExecutor.execute(command);
        Category category = result.getCategory();
        CategoryDTO categoryDTO = createCategoryDTO(category);
        return categoryDTO;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, value = "/rest/category/{categoryId}")
    public void updateCategory(@PathVariable Long categoryId,
                               @RequestBody CategoryDTO categoryDTO) {
        UpdateCategoryCommand command = updateCategoryCommand(categoryDTO);
        UpdateCategoryResult result = commandExecutor.execute(command);
    }

    private UpdateCategoryCommand updateCategoryCommand(CategoryDTO categoryDTO) {
        return new UpdateCategoryCommand(
                categoryDTO.getCategoryId(),
                categoryDTO.getName()
        );
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value = "/rest/category/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        DeleteCategoryCommand command = new DeleteCategoryCommand(categoryId);
        DeleteCategoryResult result = commandExecutor.execute(command);
    }

}
