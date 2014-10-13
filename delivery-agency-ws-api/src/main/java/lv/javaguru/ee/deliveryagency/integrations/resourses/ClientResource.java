package lv.javaguru.ee.deliveryagency.integrations.resourses;

import lv.javaguru.ee.deliveryagency.integrations.RestException;
import lv.javaguru.ee.deliveryagency.integrations.domain.ClientDTO;

/**
 * Created by Viktor on 19/09/2014.
 */
public interface ClientResource {

  static final String CREATE_CLIENT_URL = "/rest/delivery/{deliveryId}/client";
  static final String CLIENT_URL = "/rest/delivery/{deliveryId}/client/{clientId}";


  ClientDTO createClient(Long deliveryId, ClientDTO clientDTO) throws RestException;

  ClientDTO getClient(Long deliveryId, Long clientId) throws RestException;

  ClientDTO deleteClient(Long deliveryId, Long clientId) throws RestException;

  ClientDTO updateClient(Long deliveryId, Long clientId, ClientDTO clientDTO) throws RestException;

}
