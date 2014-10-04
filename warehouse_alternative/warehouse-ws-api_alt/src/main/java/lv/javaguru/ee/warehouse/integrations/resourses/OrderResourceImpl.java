package lv.javaguru.ee.warehouse.integrations.resourses;

import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.OrderDTO;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author dell
 */
public class OrderResourceImpl implements OrderResource {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private String baseWebServiceUrl;
    
    @Override
    public OrderDTO createIncomingOrder(OrderDTO orderDTO) throws RestException {        
        try {
            
            String url = baseWebServiceUrl + CREATE_IN_ORDER_URL;
            return REST_TEMPLATE.postForObject(url, orderDTO, OrderDTO.class);
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    @Override
    public OrderDTO createOutgoingOrder(OrderDTO orderDTO) throws RestException {        
        try {            
            String url = baseWebServiceUrl + CREATE_OUT_ORDER_URL;
            return REST_TEMPLATE.postForObject(url, orderDTO, OrderDTO.class);
        } catch (Throwable ex) {
            throw new RestException(ex);
        }                
    }

    public String getBaseWebServiceUrl() {
        return baseWebServiceUrl;
    }

    public void setBaseWebServiceUrl(String baseWebServiceUrl) {
        this.baseWebServiceUrl = baseWebServiceUrl;
    }
            
}
