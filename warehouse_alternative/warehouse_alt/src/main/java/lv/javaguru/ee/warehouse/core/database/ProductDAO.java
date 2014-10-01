package lv.javaguru.ee.warehouse.core.database;

import lv.javaguru.ee.warehouse.core.domain.Product;

/**
 *
 * @author dell
 */
public interface ProductDAO extends CRUDOperationDAO<Product, Long> {  
    
    public Product getByCode(Long code);
    
}
