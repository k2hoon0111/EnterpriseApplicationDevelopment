package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class BookRESTTest extends EmbeddedJettyTest {

  @Test
  public void testCreateBook() {

    CategoryDTO defaultCategoryDTO = DefaultObjectsFixture.createDefaultCategory();
    CategoryDTO createdCategoryDTO = RestFixture.createCategory(defaultCategoryDTO);
    Long categoryId = createdCategoryDTO.getCategoryId();

    BookDTO bookDTO = DefaultObjectsFixture.createDefaultBook();
    BookDTO createdBookDTO = RestFixture.createBook(categoryId, bookDTO);

    MatcherAssert.assertThat(createdBookDTO.getBookId(), is(notNullValue()));
  }

  @Test
  public void testDeleteBook() {
    CategoryDTO defaultCategoryDTO = DefaultObjectsFixture.createDefaultCategory();
    CategoryDTO createdCategoryDTO = RestFixture.createCategory(defaultCategoryDTO);
    Long createdCategoryId = createdCategoryDTO.getCategoryId();

    BookDTO bookDTO = DefaultObjectsFixture.createDefaultBook();
    BookDTO createdBookDTO = RestFixture.createBook(createdCategoryId, bookDTO);

    Long createdBookId = createdBookDTO.getBookId();

    RestFixture.deleteBook(createdCategoryId, createdBookId);

    try {
      RestFixture.getBook(createdCategoryId, createdBookId);
    } catch (RestException e) {
      assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getHttpStatus());
    }
  }

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
