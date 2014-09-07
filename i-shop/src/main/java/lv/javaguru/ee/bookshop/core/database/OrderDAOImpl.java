package lv.javaguru.ee.bookshop.core.database;

import lv.javaguru.ee.bookshop.core.domain.Order;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
@Component
public class OrderDAOImpl extends CRUDOperationDAOImpl<Order, Long> implements OrderDAO {

}
