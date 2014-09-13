package lv.javaguru.ee.bookshop.core.database.hibernate;

import lv.javaguru.ee.bookshop.core.database.AddressDAO;
import lv.javaguru.ee.bookshop.core.domain.Address;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
@Component
public class AddressDAOImpl extends CRUDOperationDAOImpl<Address, Long> implements AddressDAO {

}
