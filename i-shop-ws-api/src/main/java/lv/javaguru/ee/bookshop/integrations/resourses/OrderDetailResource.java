package lv.javaguru.ee.bookshop.integrations.resourses;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDetailDTO;

public interface OrderDetailResource {

    static final String CREATE_ORDERDETAIL_URL = "/rest/book/{bookId}/order/{orderId}/orderdetail";
    static final String GET_ORDERDETAIL_URL = "/rest/book/{bookId}/order/{orderId}/orderdetail{orderDetailId}";
    static final String UPDATE_ORDERDETAIL_URL = "/rest/orderdetail{orderDetailId}";
    static final String DELETE_ORDERDETAIL_URL = "/rest/orderdetail{orderDetailId}";

    OrderDetailDTO createOrderDetail(Long bookId, Long orderId, OrderDetailDTO orderDetailDTO) throws RestException;

    OrderDetailDTO getOrderDetail(Long bookId, Long orderId, Long orderDetailId) throws RestException;

    OrderDetailDTO updateOrderDetail(Long orderDetailId, OrderDetailDTO orderDetailDTO) throws RestException;

    void deleteOrderDetail(Long orderDetailId) throws RestException;

}
