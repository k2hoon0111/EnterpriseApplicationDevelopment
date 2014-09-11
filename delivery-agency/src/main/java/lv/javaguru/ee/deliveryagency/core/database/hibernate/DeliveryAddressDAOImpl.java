package lv.javaguru.ee.deliveryagency.core.database.hibernate;

import lv.javaguru.ee.deliveryagency.core.database.DeliveryAddressDAO;
import org.springframework.stereotype.Component;

import lv.javaguru.ee.deliveryagency.core.domain.DeliveryAddress;

@Component
public class DeliveryAddressDAOImpl extends CRUDOperationDAOImpl<DeliveryAddress, Long> implements DeliveryAddressDAO {

}
