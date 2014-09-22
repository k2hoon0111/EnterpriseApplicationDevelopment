package lv.javaguru.ee.warehouse.core.database.hibernate;

import lv.javaguru.ee.warehouse.core.domain.Order;
import lv.javaguru.ee.warehouse.core.database.OrderDAO;

import org.hibernate.LockOptions;
import org.springframework.stereotype.Component;

/**
 * Created by Yuri D. on 2014.09.23..
 */
@Component
public class OrderDAOImpl extends CRUDOperationDAOImpl<Order, Long> implements OrderDAO {

    public Order getById(Long id /*, lockOptions*/) {
        return (Order) getCurrentSession().get(Order.class, id);
    }


}
