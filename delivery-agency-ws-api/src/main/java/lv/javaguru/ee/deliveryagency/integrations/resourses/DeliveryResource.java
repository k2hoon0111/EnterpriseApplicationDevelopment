package lv.javaguru.ee.deliveryagency.integrations.resourses;

import lv.javaguru.ee.deliveryagency.integrations.RestException;
import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;

/**
 * Created by Viktor on 19/09/2014.
 */
public interface DeliveryResource {

    static final String DELIVERY_URL = "/rest/delivery";


    DeliveryDTO createDelivery(DeliveryDTO deliveryDTO) throws RestException;

    DeliveryDTO getDelivery(Long deliveryId) throws RestException;

}
