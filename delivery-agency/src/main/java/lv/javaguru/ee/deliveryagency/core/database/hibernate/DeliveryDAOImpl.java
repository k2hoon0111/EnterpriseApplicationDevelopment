package lv.javaguru.ee.deliveryagency.core.database.hibernate;

import lv.javaguru.ee.deliveryagency.core.database.DeliveryDAO;
import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
import org.springframework.stereotype.Component;

/**
 * Created by Viktor on 03/08/2014.
 */
@Component
public class DeliveryDAOImpl extends CRUDOperationDAOImpl<Delivery, Long> implements DeliveryDAO {

}
