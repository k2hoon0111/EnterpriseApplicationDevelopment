package lv.javaguru.ee.warehouse.integrations.resourses;

import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.OrderDTO;

/**
 *
 * @author dell
 */
public interface OrderResource {
    
    static final String CREATE_IN_ORDER_URL = "/rest/order/incoming/";
    static final String CREATE_OUT_ORDER_URL = "/rest/order/outgoing/";
    
    OrderDTO createIncomingOrder(OrderDTO orderDTO) throws RestException;
    
    OrderDTO createOutgoingOrder(OrderDTO orderDTO) throws RestException;
    
}
