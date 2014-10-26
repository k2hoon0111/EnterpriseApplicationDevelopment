package lv.javaguru.ee.bookshop.core.database;

import lv.javaguru.ee.bookshop.core.domain.mongo.Category;
import org.springframework.data.repository.Repository;

import java.math.BigInteger;

/**
 * Created by MumboJumbo on 26/10/14.
 */
public interface CategoryRepository extends Repository<Category, BigInteger> {

    /**
     * Returns the Category with the given identifier.
     *
     * @param id
     * @return
     */
    Category findOne(BigInteger id);

    /**
     * Returns the Category with the given identifier.
     *
     * @param name
     * @return
     */
    Category findByName(String name);

    /**
     * Saves the given {@link lv.javaguru.ee.bookshop.core.domain.mongo.Category}. #
     *
     * @param category
     * @return
     */
    Category save(Category category);

    void remove(Category category);




}
