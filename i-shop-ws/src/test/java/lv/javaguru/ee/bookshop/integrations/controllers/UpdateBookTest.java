package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class UpdateBookTest extends EmbeddedJettyTest {

    @Test
    public void testUpdateBook() {
        // Create book
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
        MatcherAssert.assertThat(createdBookDTO.getBookId(), is(notNullValue()));
        MatcherAssert.assertThat(createdBookDTO.getTitle(), is("Test title"));

        // Update book
        BookDTO bookDTOForUpdate = new BookDTO();
        bookDTOForUpdate.setBookId(createdBookDTO.getBookId());
        bookDTOForUpdate.setTitle("Test updated title");
        bookDTOForUpdate.setDescription("Test des");
        bookDTOForUpdate.setPrice(new BigDecimal("22.20"));
        bookDTOForUpdate.setYear(2003);
        bookDTOForUpdate.setAuthor("Test author");
        bookDTOForUpdate.setIsbn("19384601239756");
        bookDTOForUpdate.setCategoryId(createdBookDTO.getCategoryId());

        RestFixture.updateBook(bookDTOForUpdate);


        ResponseEntity<BookDTO> getBookResponse
                = RestFixture.getBook(bookDTOForUpdate.getBookId());

        BookDTO getBookDTO = getBookResponse.getBody();
        MatcherAssert.assertThat(getBookDTO.getBookId(), CoreMatchers.is(bookDTOForUpdate.getBookId()));
        MatcherAssert.assertThat(getBookDTO.getTitle(), is("Test updated title"));


    }

}
