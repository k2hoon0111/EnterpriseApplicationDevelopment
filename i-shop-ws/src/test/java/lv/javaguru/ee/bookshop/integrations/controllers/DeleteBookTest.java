package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class DeleteBookTest extends EmbeddedJettyTest {

    @Test
    public void testDeleteBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Test title");
        bookDTO.setDescription("Test des");
        bookDTO.setPrice(new BigDecimal("22.20"));
        bookDTO.setYear(2003);
        bookDTO.setAuthor("Test author");
        bookDTO.setIsbn("19384601239756");

        ResponseEntity<BookDTO> createBookResponse
                = RestFixture.createBook(Long.valueOf(26), bookDTO);
        BookDTO createdBookDTO = createBookResponse.getBody();

        Long bookId = createdBookDTO.getBookId();

        RestFixture.deleteBook(bookId);


        try {
            RestFixture.getBook(Long.valueOf(bookId));
        } catch (HttpClientErrorException e) {
            assertEquals("Book id not valid", e.getResponseBodyAsString());
            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
        }

    }

}
