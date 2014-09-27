package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateBookTest extends EmbeddedJettyTest {

    @Test
    public void testCreateBook() {

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
        MatcherAssert.assertThat(createdBookDTO.getBookId(), is(notNullValue()));
    }

}
