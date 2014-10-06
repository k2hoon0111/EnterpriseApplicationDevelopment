package lv.javaguru.ee.warehouse.core.database.hibernate;

import java.util.List;
import lv.javaguru.ee.warehouse.core.database.ProductPropertiesDAO;
import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.ProductProperties;
import static org.hibernate.criterion.Restrictions.and;
import static org.hibernate.criterion.Restrictions.eq;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class ProductPropertiesDAOImpl extends CRUDOperationDAOImpl<ProductProperties, Long> implements ProductPropertiesDAO {
    
    @Override
    public ProductProperties getByProductAndPropertyName(Product product, String name) {
        
        List<ProductProperties> prodProp = getCurrentSession().createCriteria(daoType) 
                                    .add(and(eq("product", product), eq("name", name)))                                    
                                    .setFirstResult(0)
                                    .setMaxResults(1)
                                    .list();        
        if (prodProp.isEmpty()) {
            return null;
        }  
        return prodProp.get(0);
        
    }
    
}
