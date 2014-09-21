package lv.javaguru.ee.warehouse.core.database.hibernate;

import lv.javaguru.ee.warehouse.core.database.WarehouseDAO;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class WarehouseDAOImpl extends CRUDOperationDAOImpl<Warehouse, Long> implements WarehouseDAO {
    
}
