package lv.javaguru.ee.warehouse.core.database;

import lv.javaguru.ee.warehouse.core.domain.Warehouse;

/**
 *
 * @author dell
 */
public interface WarehouseDAO extends CRUDOperationDAO<Warehouse, Long> {
    
    Warehouse getByTitle(String title);
    
}
