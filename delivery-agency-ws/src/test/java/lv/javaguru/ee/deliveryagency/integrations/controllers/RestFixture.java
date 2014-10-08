package lv.javaguru.ee.deliveryagency.integrations.controllers;

import lv.javaguru.ee.deliveryagency.integrations.PropertiesReader;
import lv.javaguru.ee.deliveryagency.integrations.domain.ClientDTO;
import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;
import lv.javaguru.ee.deliveryagency.integrations.resourses.ClientResource;
import lv.javaguru.ee.deliveryagency.integrations.resourses.ClientResourceImpl;
import lv.javaguru.ee.deliveryagency.integrations.resourses.DeliveryResource;
import lv.javaguru.ee.deliveryagency.integrations.resourses.DeliveryResourceImpl;

/**
 * Created by Viktor on 16/09/2014.
 */
public class RestFixture {

    private static DeliveryResource deliveryResource = createDeliveryResource();
    private static ClientResource clientResource = createClientResource();

    private static DeliveryResource createDeliveryResource() {
        PropertiesReader propertiesReader = new PropertiesReader();
        String baseUrl = propertiesReader.getBaseUrl();
        return new DeliveryResourceImpl(baseUrl);
    }

	private static ClientResource createClientResource() {
		PropertiesReader propertiesReader = new PropertiesReader();
		String baseUrl = propertiesReader.getBaseUrl();
		return new ClientResourceImpl(baseUrl);
	}


	/////////// Delivery methods /////////////
    public static DeliveryDTO createDelivery(DeliveryDTO deliveryDTO) {
        return deliveryResource.createDelivery(deliveryDTO);
    }

    public static DeliveryDTO getDelivery(Long deliveryId) {
        return deliveryResource.getDelivery(deliveryId);
    }

    public static DeliveryDTO createDelivery() {
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        return RestFixture.createDelivery(deliveryDTO);
    }

////////////// Client methods ////////////////
    public static ClientDTO createClient(Long deliveryId, ClientDTO clientDTO) {
	    return clientResource.createClient(deliveryId, clientDTO); 
    }

    public static ClientDTO getClient(Long deliveryId, Long clientId) {
	    return clientResource.getClient(deliveryId, clientId);
    }

    public static ClientDTO deleteClient(Long deliveryId, Long clientId) {
        return clientResource.deleteClient(deliveryId, clientId);
    }

    public static ClientDTO updateClient(Long deliveryId, Long clientId, ClientDTO clientDTO) {
        return clientResource.updateClient(deliveryId, clientId, clientDTO);
    }

}
