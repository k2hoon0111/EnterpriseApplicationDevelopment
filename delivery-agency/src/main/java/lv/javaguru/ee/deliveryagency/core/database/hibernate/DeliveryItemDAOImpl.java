package lv.javaguru.ee.deliveryagency.core.database.hibernate;

import lv.javaguru.ee.deliveryagency.core.database.DeliveryItemDAO;
import lv.javaguru.ee.deliveryagency.core.domain.DeliveryItem;
import org.springframework.stereotype.Component;

/**
 * Created by Viktor on 02/08/2014.
 */
@Component
public class DeliveryItemDAOImpl extends CRUDOperationDAOImpl<DeliveryItem, Long> implements DeliveryItemDAO {

}
