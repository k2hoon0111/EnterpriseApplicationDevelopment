//package lv.javaguru.ee.bookshop.core.database;
//
//
//import lv.javaguru.ee.bookshop.core.domain.Category;
//import org.junit.Assert;
//import org.junit.Test;
//
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//
///**
// * Created with IntelliJ IDEA.
// * User: MumboJumbo
// * Date: 09/09/14
// * Time: 15:33
// * To change this template use File | Settings | File Templates.
// */
//public class CategoryDAOImplIntegrationTest extends DatabaseIntegrationTest {
//    @Test
//    public void testCreateCategory() {
//        Category category = getDefaultCategory();
//        Assert.assertNotNull(category);
//        assertThat(category.getCategoryId(), is(nullValue()));
//        saveCategory(category);
//        assertThat(category.getCategoryId(), is(notNullValue()));
//    }
//}
