package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.DefaultObjectsFixture;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateBookTest extends EmbeddedJettyTest {

    @Test
    public void testCreateBook() {

        CategoryDTO defaultCategoryDTO = DefaultObjectsFixture.createDefaultCategory();
        CategoryDTO createdCategoryDTO = RestFixture.createCategory(defaultCategoryDTO);
        Long categoryId = createdCategoryDTO.getCategoryId();

        BookDTO bookDTO = DefaultObjectsFixture.createDefaultBook();
        BookDTO createdBookDTO = RestFixture.createBook(categoryId, bookDTO);

        MatcherAssert.assertThat(createdBookDTO.getBookId(), is(notNullValue()));
    }

}
