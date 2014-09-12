package lv.javaguru.ee.bookshop.core.database.hibernate;

import lv.javaguru.ee.bookshop.core.database.CategoryDAO;
import lv.javaguru.ee.bookshop.core.domain.Category;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 13:41
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CategoryDAOImpl extends CRUDOperationDAOImpl<Category, Long> implements CategoryDAO {

}
