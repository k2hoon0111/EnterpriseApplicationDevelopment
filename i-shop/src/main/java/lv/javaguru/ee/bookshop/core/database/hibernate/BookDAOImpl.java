package lv.javaguru.ee.bookshop.core.database.hibernate;

import lv.javaguru.ee.bookshop.core.database.BookDAO;
import lv.javaguru.ee.bookshop.core.domain.Book;
import org.hibernate.LockOptions;
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
    public Book getById(Long bookId, LockOptions lockOptions) {
        return (Book) getCurrentSession().get(Book.class, bookId, lockOptions);
    }

}
