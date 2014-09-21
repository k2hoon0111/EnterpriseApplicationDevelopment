package lv.javaguru.ee.warehouse.core.database.hibernate;

import lv.javaguru.ee.warehouse.core.database.ProductPropertiesDAO;
import lv.javaguru.ee.warehouse.core.domain.ProductProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class ProductPropertiesDAOImpl extends CRUDOperationDAOImpl<ProductProperties, Long> implements ProductPropertiesDAO {
    
}
