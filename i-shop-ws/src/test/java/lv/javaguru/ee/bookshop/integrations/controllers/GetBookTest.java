package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Viktor on 16/09/2014.
 */
public class GetBookTest extends EmbeddedJettyTest {

    @Test
    public void testGetBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Test title");
        bookDTO.setDescription("Test des");
        bookDTO.setPrice(new BigDecimal("22.20"));
        bookDTO.setYear(2003);
        bookDTO.setAuthor("Test author");
        bookDTO.setIsbn("19384601239756");

        ResponseEntity<BookDTO> createBookResponse
//                = RestFixture.createBook(createDeliveryDTO.getDeliveryId(), bookDTO);
                = RestFixture.createBook(Long.valueOf(26), bookDTO);
        BookDTO createBookDTO = createBookResponse.getBody();

        Long bookId = createBookDTO.getBookId();

        ResponseEntity<BookDTO> getBookResponse
                = RestFixture.getBook(bookId);

        BookDTO getBookDTO = getBookResponse.getBody();
        assertThat(getBookDTO.getBookId(), is(bookId));
        assertThat(getBookDTO.getTitle(), is("Test title"));
        assertThat(getBookDTO.getIsbn(), is("19384601239756"));
    }

    @Test
    public void testGetBookWithWrongId() {
        try {
            RestFixture.getBook(Long.MAX_VALUE);
        } catch (HttpClientErrorException e) {
            assertEquals("Book id not valid", e.getResponseBodyAsString());
            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
        }
    }

}
