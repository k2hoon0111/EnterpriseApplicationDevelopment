package lv.javaguru.ee.bookshop.core.database.hibernate;

import lv.javaguru.ee.bookshop.core.database.RoleDAO;
import lv.javaguru.ee.bookshop.core.domain.Role;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 13:49
 * To change this template use File | Settings | File Templates.
 */
@Component
public class RoleDAOImpl extends CRUDOperationDAOImpl<Role, Long> implements RoleDAO {

}
