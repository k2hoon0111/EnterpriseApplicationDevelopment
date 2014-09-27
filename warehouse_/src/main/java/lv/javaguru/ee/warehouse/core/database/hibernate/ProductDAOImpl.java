package lv.javaguru.ee.warehouse.core.database.hibernate;

import lv.javaguru.ee.warehouse.core.database.ProductDAO;
import lv.javaguru.ee.warehouse.core.domain.Product;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class ProductDAOImpl extends CRUDOperationDAOImpl<Product, Long> implements ProductDAO {
    
}
