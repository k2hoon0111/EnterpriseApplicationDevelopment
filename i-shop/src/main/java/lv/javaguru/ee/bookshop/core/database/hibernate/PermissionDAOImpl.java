package lv.javaguru.ee.bookshop.core.database.hibernate;

import lv.javaguru.ee.bookshop.core.database.PermissionDAO;
import lv.javaguru.ee.bookshop.core.domain.Permission;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PermissionDAOImpl extends CRUDOperationDAOImpl<Permission, Long> implements PermissionDAO {

}
