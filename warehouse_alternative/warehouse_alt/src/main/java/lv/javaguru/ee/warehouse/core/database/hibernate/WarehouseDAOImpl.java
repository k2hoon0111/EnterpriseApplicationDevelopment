package lv.javaguru.ee.warehouse.core.database.hibernate;

import java.util.List;
import lv.javaguru.ee.warehouse.core.database.WarehouseDAO;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;
import static org.hibernate.criterion.Restrictions.eq;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class WarehouseDAOImpl extends CRUDOperationDAOImpl<Warehouse, Long> implements WarehouseDAO {

    @Override
    public Warehouse getByTitle(String title) {
        List<Warehouse> products = getCurrentSession().createCriteria(daoType) 
                                    .add(eq("title", title))
                                    .setFirstResult(0)
                                    .setMaxResults(1)
                                    .list();        
        if (products.isEmpty()) {
            return null;
        }  
        return products.get(0);
    }
    
}
