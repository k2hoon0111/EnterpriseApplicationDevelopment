//package lv.javaguru.ee.bookshop.integrations.controllers;
//
//import lv.javaguru.ee.bookshop.core.CommandExecutor;
//import lv.javaguru.ee.bookshop.core.commands.CreateBookCommand;
//import lv.javaguru.ee.bookshop.core.commands.CreateBookCommandResult;
//import lv.javaguru.ee.bookshop.core.commands.GetBookCommand;
//import lv.javaguru.ee.bookshop.core.commands.GetBookResult;
//import lv.javaguru.ee.bookshop.core.domain.Book;
//import lv.javaguru.ee.bookshop.core.domain.Category;
//import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
//import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
///**
// * Created by MumboJumbo on 17/09/14.
// */
//
//@Controller
//public class CategoryController {
//
//    @Autowired
//    private CommandExecutor commandExecutor;
//
//
//    @RequestMapping(method = RequestMethod.POST, value = "/rest/category/")
//    public ResponseEntity<CategoryDTO> createCategory(@PathVariable Long categoryId,
//                                                      @RequestBody CategoryDTO categoryDTO) {
//        CreateCategoryCommand command = createCategoryCommand(categoryId, categoryDTO);
//        CreateCategoryCommandResult result = commandExecutor.execute(command);
//
//        Book book = result.getCategory();
//        CategoryDTO resultDTO = createCategoryDTO(category);
//
//        return new ResponseEntity<CategoryDTO>(resultDTO, HttpStatus.CREATED);
//    }
//
//    private CategoryDTO createCategoryDTO(Category category) {
//        CategoryDTO categoryDTO = new CategoryDTO();
//        categoryDTO.setName("C++");
//        return categoryDTO;
//    }
//
//    private CreateCategoryCommand createCategoryCommand(CategoryDTO categoryDTO) {
//        return new CreateCategoryCommand(
//                categoryDTO.getName()
//        );
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/rest/category/{categoryId}")
//    public ResponseEntity<BookDTO> getClient(@PathVariable Long categoryId) {
//        GetCategoryCommand command = new GetCategoryCommand(categoryId);
//        GetCategoryResult result = commandExecutor.execute(command);
//        Category category = result.getCategory();
//        CategoryDTO categoryDTO = createCategoryDTO(category);
//        return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
//    }
//
//}
