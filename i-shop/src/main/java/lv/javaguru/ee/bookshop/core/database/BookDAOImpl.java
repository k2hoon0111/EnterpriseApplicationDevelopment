package lv.javaguru.ee.bookshop.core.database;

import lv.javaguru.ee.bookshop.core.domain.Book;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 13:39
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BookDAOImpl extends CRUDOperationDAOImpl<Book, Long> implements BookDAO {

}
