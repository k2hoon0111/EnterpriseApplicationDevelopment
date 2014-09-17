package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by MumboJumbo on 17/09/14.
 */
public class CreateBookTest extends EmbeddedJettyTest {
    @Test
    public void testCreateBook() {
        BookDTO bookDTO = new BookDTO();

        ResponseEntity<BookDTO> createBookResponse
                = RestFixture.createBook(bookDTO);

        BookDTO createBookDTO = createBookResponse.getBody();
        assertThat(createBookDTO.getBookId(), is(notNullValue()));
    }
}
