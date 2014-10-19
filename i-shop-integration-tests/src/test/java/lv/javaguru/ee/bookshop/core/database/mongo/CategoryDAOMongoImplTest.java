package lv.javaguru.ee.bookshop.core.database.mongo;

import lv.javaguru.ee.bookshop.core.database.CategoryDAO;
import lv.javaguru.ee.bookshop.core.domain.Category;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by MumboJumbo on 19/10/14.
 */
public class CategoryDAOMongoImplTest {
    private CategoryDAO categoryDAO = new CategoryDAOMongoImpl();

    @Test
    public void testCreateCategory() {
        Category category = new Category();

        category.setCategoryId(2L);
        category.setName("JAVA");
        categoryDAO.create(category);

        Category createdCategory = categoryDAO.getById(2L);
        assertThat(createdCategory.getName(), is("JAVA"));

        categoryDAO.delete(category);
    }

    @Test
    public void testUpdateCategory() {
        Category category = new Category();
        Category categoryForUpdate = new Category();

        category.setCategoryId(1L);
        category.setName("Mongo DB");
        categoryDAO.create(category);

        Category createdCategory = categoryDAO.getById(1L);
        assertThat(createdCategory.getName(), is("Mongo DB"));

        categoryForUpdate.setCategoryId(1L);
        categoryForUpdate.setName("JAVA");
        categoryDAO.update(categoryForUpdate);

        Category updatedCategory = categoryDAO.getById(1L);
        assertThat(updatedCategory.getName(), is("JAVA"));

        categoryDAO.delete(updatedCategory);
    }

    @Test
    public void testgetAll() {
        Category category1 = new Category();
        Category category2 = new Category();

        category1.setCategoryId(1L);
        category1.setName("Mongo DB");
        categoryDAO.create(category1);

        category2.setCategoryId(2L);
        category2.setName("JAVA");
        categoryDAO.create(category2);

        List<Category> categoryList = categoryDAO.getAll();
        assertThat(categoryList, is(notNullValue()));

        categoryDAO.delete(category1);
        categoryDAO.delete(category2);
    }

    @Test
    public void testDeleteCategory() {
        Category category = new Category();

        category.setCategoryId(2L);
        category.setName("JAVA");
        categoryDAO.create(category);

        Category createdCategory = categoryDAO.getById(2L);
        assertThat(createdCategory.getName(), is("JAVA"));

        categoryDAO.delete(category);

        Category deletedCategory = categoryDAO.getById(2L);
        assertThat(deletedCategory, is(nullValue()));

    }

}
