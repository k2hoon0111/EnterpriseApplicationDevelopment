package lv.javaguru.ee.warehouse.core.database;

import lv.javaguru.ee.warehouse.core.domain.Order;

/**
 * Created by Yuri D. on 2014.09.23..
 */
public interface OrderDAO extends CRUDOperationDAO<Order, Long> {

    Order getById(Long id);

}
