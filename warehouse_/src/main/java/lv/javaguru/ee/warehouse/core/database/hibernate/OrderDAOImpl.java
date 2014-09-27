package lv.javaguru.ee.warehouse.core.database.hibernate;

import lv.javaguru.ee.warehouse.core.database.OrderDAO;
import lv.javaguru.ee.warehouse.core.domain.Order;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class OrderDAOImpl extends CRUDOperationDAOImpl<Order, Long> implements OrderDAO {
    
}
