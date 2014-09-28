package lv.javaguru.ee.bookshop.integrations.resourses;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class OrderResourceImpl implements OrderResource {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private String baseWebServiceUrl;


    public OrderResourceImpl(String baseWebServiceUrl) {
        this.baseWebServiceUrl = baseWebServiceUrl;
    }

    @Override
    public OrderDTO createOrder(Long accountId, OrderDTO orderDTO) throws RestException {
        try {
            ResponseEntity<OrderDTO> responseEntity = REST_TEMPLATE.postForEntity(baseWebServiceUrl + CREATE_ORDER_URL.replace("{accountId}", accountId.toString()),
                    orderDTO, OrderDTO.class, new HashMap<String, String>()
            );
            return responseEntity.getBody();
        } catch (Throwable e) {
            throw new RestException(e);
        }
    }

    @Override
    public OrderDTO getOrder(Long accountId, Long orderId) throws RestException {
        try {
            String getOrderUrl = GET_ORDER_URL.replace("{accountId}", accountId.toString()).replace("{orderId}", orderId.toString());
            ResponseEntity<OrderDTO> responseEntity = REST_TEMPLATE.getForEntity(baseWebServiceUrl + getOrderUrl,
                    OrderDTO.class, new HashMap<String, String>()
            );
            return responseEntity.getBody();
        } catch (Throwable e) {
            throw new RestException(e);
        }
    }

    @Override
    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) throws RestException {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) throws RestException {

    }
}
