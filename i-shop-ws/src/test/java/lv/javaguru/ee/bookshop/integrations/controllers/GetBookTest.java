package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;

public class GetBookTest extends EmbeddedJettyTest {

    @Test
    public void testGetBook() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("C++");
        CategoryDTO createdCategoryDTO = RestFixture.createCategory(categoryDTO);
        Long categoryId = createdCategoryDTO.getCategoryId();

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Test title");
        bookDTO.setDescription("Test des");
        bookDTO.setPrice(new BigDecimal("22.20"));
        bookDTO.setYear(2003);
        bookDTO.setAuthor("Test author");
        bookDTO.setIsbn("19384601239756");

        BookDTO createdBookDTO = RestFixture.createBook(categoryId, bookDTO);

        Long bookId = createdBookDTO.getBookId();


        BookDTO getBookDTO = RestFixture.getBook(categoryId, bookId);
        MatcherAssert.assertThat(getBookDTO.getBookId(), is(bookId));
        MatcherAssert.assertThat(getBookDTO.getTitle(), is("Test title"));
        MatcherAssert.assertThat(getBookDTO.getIsbn(), is("19384601239756"));
    }

    @Test
    public void testGetOrderWithWrongId() {
        try {
            RestFixture.getCategory(Long.MAX_VALUE);
        } catch (RestException e) {
            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getHttpStatus());
        }
    }
}


