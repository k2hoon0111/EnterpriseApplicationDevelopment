package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.core.CommandExecutor;
import lv.javaguru.ee.bookshop.core.commands.CreateBookCommand;
import lv.javaguru.ee.bookshop.core.commands.CreateBookResult;
import lv.javaguru.ee.bookshop.core.commands.GetBookCommand;
import lv.javaguru.ee.bookshop.core.commands.GetBookResult;
import lv.javaguru.ee.bookshop.core.domain.Book;
import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
import lv.javaguru.ee.bookshop.integrations.resourses.BookResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by MumboJumbo on 17/09/14.
 */

@RestController
public class BookController implements BookResource {

    @Autowired
    private CommandExecutor commandExecutor;

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/rest/category/{categoryId}/book")
    public BookDTO createBook(@PathVariable Long categoryId,
                              @RequestBody BookDTO bookDTO) {
        CreateBookCommand command = createBookCommand(categoryId, bookDTO);
        CreateBookResult result = commandExecutor.execute(command);

        Book book = result.getBook();
        BookDTO resultDTO = createBookDTO(book);

        return resultDTO;
    }

    private BookDTO createBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(book.getBookId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setYear(book.getYear());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setCategoryId(book.getCategory().getCategoryId());

        return bookDTO;
    }

    private CreateBookCommand createBookCommand(Long categoryId, BookDTO bookDTO) {
        return new CreateBookCommand(
                bookDTO.getTitle(),
                bookDTO.getDescription(),
                bookDTO.getPrice(),
                bookDTO.getYear(),
                bookDTO.getAuthor(),
                bookDTO.getIsbn(),
                categoryId
        );
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/rest/category/{categoryId}/book/{bookId}")
    public BookDTO getBook(@PathVariable Long categoryId,
                           @PathVariable Long bookId) {
        GetBookCommand command = new GetBookCommand(categoryId, bookId);
        GetBookResult result = commandExecutor.execute(command);
        Book book = result.getBook();
        BookDTO bookDTO = createBookDTO(book);

        return bookDTO;

    }

//    @RequestMapping(method = RequestMethod.PUT, value = "/rest/book/{bookId}")
//    public ResponseEntity<BookDTO> UpdateBook(@RequestBody BookDTO bookDTO) {
//        UpdateBookCommand command = updateBookCommand(bookDTO);
//        UpdateBookResult result = commandExecutor.execute(command);
//
//        return new ResponseEntity<BookDTO>(HttpStatus.OK);
//    }
//
//    private UpdateBookCommand updateBookCommand(BookDTO bookDTO) {
//        return new UpdateBookCommand(
//                bookDTO.getBookId(),
//                bookDTO.getTitle(),
//                bookDTO.getDescription(),
//                bookDTO.getPrice(),
//                bookDTO.getYear(),
//                bookDTO.getAuthor(),
//                bookDTO.getIsbn(),
//                bookDTO.getCategoryId()
//        );
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/rest/book/{bookId}")
//    public ResponseEntity deleteBook(@PathVariable Long bookId) {
//        DeleteBookCommand command = new DeleteBookCommand(bookId);
//        DeleteBookResult result = commandExecutor.execute(command);
//
//        return new ResponseEntity(HttpStatus.OK);
//    }

    @Override
    public BookDTO updateBook(Long bookId, BookDTO bookDTO) throws RestException {
        return null;
    }

    @Override
    public void deleteBook(Long bookId) throws RestException {

    }
}
