package lv.javaguru.ee.warehouse.integrations.resourses;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.OrderDTO;

/**
 *
 * @author dell
 */
public class OrderResourceImpl implements OrderResource {
    
    private final String baseWebServiceUrl;
    private final Client client;
    
    public OrderResourceImpl(String baseWebServiceUrl) {
        this.baseWebServiceUrl = baseWebServiceUrl;
        this.client = ClientBuilder.newClient();
    }
    
    @Override
    public OrderDTO createIncomingOrder(OrderDTO orderDTO) throws RestException {        
        try {            
            String url = baseWebServiceUrl + CREATE_IN_ORDER_URL;
            return this.client.target(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(orderDTO), OrderDTO.class);            
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    @Override
    public OrderDTO createOutgoingOrder(OrderDTO orderDTO) throws RestException {        
        try {            
            String url = baseWebServiceUrl + CREATE_OUT_ORDER_URL;            
            return this.client.target(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(orderDTO), OrderDTO.class);            
        } catch (Throwable ex) {
            throw new RestException(ex);
        }                
    }

    public String getBaseWebServiceUrl() {
        return baseWebServiceUrl;
    }
               
}
