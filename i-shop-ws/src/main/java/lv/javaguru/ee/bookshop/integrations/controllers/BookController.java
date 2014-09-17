package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.core.CommandExecutor;
import lv.javaguru.ee.bookshop.core.commands.GetBookCommand;
import lv.javaguru.ee.bookshop.core.commands.GetBookResult;
import lv.javaguru.ee.bookshop.core.domain.Book;
import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

    @Autowired
    private CommandExecutor commandExecutor;


//    @RequestMapping(method = RequestMethod.POST, value = "/rest/book")
//    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
//        CreateBookCommand createBookCommand = new CreateDeliveryCommand();
//        CreateDeliveryResult createDeliveryResult = commandExecutor.execute(createDeliveryCommand);
//
//        Delivery delivery = createDeliveryResult.getDelivery();
//        deliveryDTO.setDeliveryId(delivery.getDeliveryId());
//
//        return new ResponseEntity<DeliveryDTO>(deliveryDTO, HttpStatus.CREATED);
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/rest/book/{bookId}")
    public
    @ResponseBody
    BookDTO getDelivery(@PathVariable Long bookId) {
        GetBookCommand command = new GetBookCommand(bookId);
        GetBookResult result = commandExecutor.execute(command);
        Book book = result.getBook();

        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(book.getBookId());

        return bookDTO;
    }

}
