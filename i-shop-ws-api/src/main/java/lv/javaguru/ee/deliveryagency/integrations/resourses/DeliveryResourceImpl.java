//package lv.javaguru.ee.deliveryagency.integrations.resourses;
//
//import lv.javaguru.ee.deliveryagency.integrations.RestException;
//import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//
///**
// * Created by Viktor on 18/09/2014.
// */
//public class DeliveryResourceImpl implements DeliveryResource {
//
//    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
//
//    private String baseWebServiceUrl;
//
//
//    public DeliveryResourceImpl(String baseWebServiceUrl) {
//        this.baseWebServiceUrl = baseWebServiceUrl;
//    }
//
//    public DeliveryDTO createDelivery(DeliveryDTO deliveryDTO) throws RestException {
//        try {
//            ResponseEntity<DeliveryDTO> responseEntity = REST_TEMPLATE.postForEntity(baseWebServiceUrl + CREATE_DELIVERY_URL,
//                    deliveryDTO, DeliveryDTO.class, new HashMap<String, String>()
//            );
//            return responseEntity.getBody();
//        } catch (Throwable e) {
//            throw new RestException(e);
//        }
//    }
//
//    public DeliveryDTO getDelivery(Long deliveryId) throws RestException {
//        try {
//            String getDeliveryUrl = GET_DELIVERY_URL.replace("{id}", deliveryId.toString());
//            ResponseEntity<DeliveryDTO> responseEntity = REST_TEMPLATE.getForEntity(baseWebServiceUrl + getDeliveryUrl,
//                    DeliveryDTO.class, new HashMap<String, String>()
//            );
//            return responseEntity.getBody();
//        } catch (Throwable e) {
//            throw new RestException(e);
//        }
//    }
//
//}
