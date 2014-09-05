package lv.javaguru.ee.bookstore.core.database;

import lv.javaguru.ee.bookstore.core.domain.Account;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */
@Component
public class AccountDAOImpl extends CRUDOperationDAOImpl<Account, Long> implements AccountDAO {

}
