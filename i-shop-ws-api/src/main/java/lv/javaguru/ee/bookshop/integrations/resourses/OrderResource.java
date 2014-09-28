package lv.javaguru.ee.bookshop.integrations.resourses;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDTO;

public interface OrderResource {

    static final String CREATE_ORDER_URL = "/rest/account/{accountId}/order";
    static final String GET_ORDER_URL = "/rest/account/{accountId}/order/{orderId}";
    static final String UPDATE_ORDER_URL = "/rest/order/{orderId}";
    static final String DELETE_ORDER_URL = "/rest/order/{orderId}";


    OrderDTO createOrder(Long accountId, OrderDTO orderDTO) throws RestException;

    OrderDTO getOrder(Long accountId, Long orderId) throws RestException;

    OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) throws RestException;

    void deleteOrder(Long orderId) throws RestException;

}
