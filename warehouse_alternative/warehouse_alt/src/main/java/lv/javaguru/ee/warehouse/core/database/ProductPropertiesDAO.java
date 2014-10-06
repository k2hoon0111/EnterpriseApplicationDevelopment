package lv.javaguru.ee.warehouse.core.database;

import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.ProductProperties;

/**
 *
 * @author dell
 */
public interface ProductPropertiesDAO extends CRUDOperationDAO<ProductProperties, Long>{
    
    ProductProperties getByProductAndPropertyName(Product product, String name);
    
}
