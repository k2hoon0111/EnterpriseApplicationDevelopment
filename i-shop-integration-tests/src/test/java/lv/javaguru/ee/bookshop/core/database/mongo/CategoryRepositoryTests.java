package lv.javaguru.ee.bookshop.core.database.mongo;

import lv.javaguru.ee.bookshop.config.CoreConfig;
import lv.javaguru.ee.bookshop.core.database.CategoryDAO;
import lv.javaguru.ee.bookshop.core.database.CategoryRepository;
import lv.javaguru.ee.bookshop.core.domain.mongo.Category;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by nikoboro on 2014.10.20..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CoreConfig.class)
public class CategoryRepositoryTests {

    @Autowired
    CategoryRepository repository;

    CategoryDAO categoryDAO = new CategoryDAOMongoImpl();

    @Test
    public void create() {

        Category category = new Category("Mongo DB");

        Category savedCategory = repository.save(category);


        Category createdCategory = repository.findOne(savedCategory.getId());
        MatcherAssert.assertThat(createdCategory.getName(), CoreMatchers.is("Mongo DB"));

        repository.remove(createdCategory);
    }
//    getById
//    update
//    delete
}

