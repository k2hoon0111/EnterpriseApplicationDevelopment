package lv.javaguru.ee.deliveryagency.integrations.resourses;

import lv.javaguru.ee.deliveryagency.integrations.RestException;
import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Created by Viktor on 18/09/2014.
 */
public class DeliveryResourceImpl implements DeliveryResource {

    private String baseWebServiceUrl;
    private Client client;


    public DeliveryResourceImpl(String baseWebServiceUrl) {
        this.baseWebServiceUrl = baseWebServiceUrl;
        client = ClientBuilder.newClient();
    }

    public DeliveryDTO createDelivery(DeliveryDTO deliveryDTO) throws RestException {
        try {
            String deliveryUrl = baseWebServiceUrl + DELIVERY_URL;
            WebTarget target = client.target(deliveryUrl);
            return target.request(MediaType.APPLICATION_XML)
                    .post(Entity.entity(deliveryDTO, MediaType.APPLICATION_XML), DeliveryDTO.class);
        } catch (Throwable e) {
            throw new RestException(e);
        }
    }

    public DeliveryDTO getDelivery(Long deliveryId) throws RestException {
        try {
            String deliveryUrl = baseWebServiceUrl + DELIVERY_URL + "/" + deliveryId.toString();
            WebTarget target = client.target(deliveryUrl);
            return target.request(MediaType.APPLICATION_XML).get(DeliveryDTO.class);
        } catch (Throwable e) {
            throw new RestException(e);
        }
    }

}
