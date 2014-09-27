package lv.javaguru.ee.warehouse.core.database.hibernate;

import java.util.List;
import lv.javaguru.ee.warehouse.core.database.ProductDAO;
import lv.javaguru.ee.warehouse.core.domain.Product;
import static org.hibernate.criterion.Restrictions.eq;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class ProductDAOImpl extends CRUDOperationDAOImpl<Product, Long> implements ProductDAO {
    
    @Override
    public Product getByCode(Long code) {        
        List<Product> products = getCurrentSession().createCriteria(daoType) 
                                    .add(eq("code", code))
                                    .setFirstResult(0)
                                    .setMaxResults(1)
                                    .list();        
        if (products.isEmpty()) {
            return null;
        }        
        return products.get(0);
    }
    
}
