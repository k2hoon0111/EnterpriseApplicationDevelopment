package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;

public class UpdateBookTest extends EmbeddedJettyTest {

    @Test
    public void testUpdateBook() {
        CategoryDTO defaultCategoryDTO = DefaultObjectsFixture.createDefaultCategory();
        CategoryDTO createdCategoryDTO = RestFixture.createCategory(defaultCategoryDTO);
        Long createdCategoryId = createdCategoryDTO.getCategoryId();

        BookDTO bookDTO = DefaultObjectsFixture.createDefaultBook();
        BookDTO createdBookDTO = RestFixture.createBook(createdCategoryId, bookDTO);

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

        RestFixture.updateBook(createdCategoryId, createdBookDTO.getBookId(), bookDTOForUpdate);

        BookDTO updatedBook = RestFixture.getBook(createdCategoryId, createdBookDTO.getBookId());

        MatcherAssert.assertThat(updatedBook.getBookId(), CoreMatchers.is(bookDTOForUpdate.getBookId()));
        MatcherAssert.assertThat(updatedBook.getTitle(), is("Test updated title"));

    }

}
