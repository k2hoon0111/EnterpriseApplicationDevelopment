package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;

public class GetBookTest extends EmbeddedJettyTest {

    @Test
    public void testGetBook() {
        CategoryDTO defaultCategoryDTO = DefaultObjectsFixture.createDefaultCategory();

        CategoryDTO createdCategoryDTO = RestFixture.createCategory(defaultCategoryDTO);
        Long categoryId = createdCategoryDTO.getCategoryId();

        BookDTO defaultBookDTO = DefaultObjectsFixture.createDefaultBook();

        BookDTO createdBookDTO = RestFixture.createBook(categoryId, defaultBookDTO);

        Long bookId = createdBookDTO.getBookId();

        BookDTO getBookDTO = RestFixture.getBook(categoryId, bookId);

        MatcherAssert.assertThat(getBookDTO.getBookId(), is(bookId));
        MatcherAssert.assertThat(getBookDTO.getTitle(), is(defaultBookDTO.getTitle()));
        MatcherAssert.assertThat(getBookDTO.getIsbn(), is(defaultBookDTO.getIsbn()));
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


