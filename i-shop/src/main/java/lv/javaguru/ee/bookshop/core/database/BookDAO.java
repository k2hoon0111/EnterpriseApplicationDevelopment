package lv.javaguru.ee.bookshop.core.database;

import lv.javaguru.ee.bookshop.core.domain.Book;
import org.hibernate.LockOptions;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 */

public interface BookDAO extends CRUDOperationDAO<Book, Long> {
    Book getById(Long bookId, LockOptions lockOptions);

}