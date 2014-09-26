//package lv.javaguru.ee.deliveryagency.integrations.resourses;
//
//import java.util.HashMap;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import lv.javaguru.ee.deliveryagency.integrations.RestException;
//import lv.javaguru.ee.deliveryagency.integrations.domain.ClientDTO;
//
///**
// * Created by Viktor on 19/09/2014.
// */
//public class ClientResourceImpl implements ClientResource {
//
//    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
//
//    private String baseWebServiceUrl;
//
//
//    public ClientResourceImpl(String baseWebServiceUrl) {
//        this.baseWebServiceUrl = baseWebServiceUrl;
//    }
//
//    public ClientDTO createClient(Long deliveryId, ClientDTO clientDTO) throws RestException {
//        try {
//	        String createClientUrl = CREATE_CLIENT_URL.replace("{deliveryId}", deliveryId.toString());
//            ResponseEntity<ClientDTO> responseEntity = REST_TEMPLATE.postForEntity(baseWebServiceUrl + createClientUrl,
//                    clientDTO, ClientDTO.class, new HashMap<String, String>()
//            );
//            return responseEntity.getBody();
//        } catch (Throwable e) {
//            throw new RestException(e);
//        }
//    }
//
//    public ClientDTO getClient(Long deliveryId, Long clientId) throws RestException {
//        try {
//            String getClientUrl = GET_CLIENT_URL.replace("{deliveryId}", deliveryId.toString())
//		                                        .replace("{clientId}", clientId.toString());
//            ResponseEntity<ClientDTO> responseEntity = REST_TEMPLATE.getForEntity(baseWebServiceUrl + getClientUrl,
//                    ClientDTO.class, new HashMap<String, String>()
//            );
//            return responseEntity.getBody();
//        } catch (Throwable e) {
//            throw new RestException(e);
//        }
//    }
//
//}
