package lv.javaguru.ee.bookshop.core.database;

import lv.javaguru.ee.bookshop.core.domain.Book;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 09/09/14
 * Time: 09:38
 * To change this template use File | Settings | File Templates.
 */
public class BookDAOImplIntegrationTest extends DatabaseIntegrationTest {
    @Test
    public void testCreateBook() {
        Book book = createDefaultBook();
        Assert.assertNotNull(book);
//        assertThat(book.getBookId(), is(nullValue()));
//        saveBoook(book);
//        assertThat(book.getBookId(), is(notNullValue()));
    }
}
