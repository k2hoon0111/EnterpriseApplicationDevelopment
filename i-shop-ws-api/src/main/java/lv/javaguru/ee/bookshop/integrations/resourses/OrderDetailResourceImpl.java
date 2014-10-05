package lv.javaguru.ee.bookshop.integrations.resourses;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDetailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class OrderDetailResourceImpl implements OrderDetailResource {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private String baseWebServiceUrl;


    public OrderDetailResourceImpl(String baseWebServiceUrl) {
        this.baseWebServiceUrl = baseWebServiceUrl;
    }

    @Override
    public OrderDetailDTO createOrderDetail(Long bookId, Long orderId, OrderDetailDTO orderDetailDTO) throws RestException {
        try {
            ResponseEntity<OrderDetailDTO> responseEntity =
                    REST_TEMPLATE.postForEntity(baseWebServiceUrl + CREATE_ORDERDETAIL_URL.
                                    replace("{bookId}", bookId.toString()).
                                    replace("{orderId}", orderId.toString()),
                            orderDetailDTO, OrderDetailDTO.class, new HashMap<String, String>()
                    );
            return responseEntity.getBody();
        } catch (Throwable e) {
            throw new RestException(e);
        }
    }

    @Override
    public OrderDetailDTO getOrderDetail(Long bookId, Long orderId, Long orderDetailId) throws RestException {
        try {
            String getOrderDetailUrl = GET_ORDERDETAIL_URL.replace("{bookId}", bookId.toString()).
                    replace("{orderId}", orderId.toString()).
                    replace("{orderDetailId}", orderDetailId.toString());
            ResponseEntity<OrderDetailDTO> responseEntity = REST_TEMPLATE.getForEntity(baseWebServiceUrl + getOrderDetailUrl,
                    OrderDetailDTO.class, new HashMap<String, String>()
            );
            return responseEntity.getBody();
        } catch (Throwable e) {
            throw new RestException(e);
        }
    }

    @Override
    public void updateOrderDetail(Long bookId, Long orderId, Long orderDetailId, OrderDetailDTO orderDetailDTO) throws RestException {
        try {
            String updateOrderDetailUrl = UPDATE_ORDERDETAIL_URL.replace("{bookId}", bookId.toString()).
                    replace("{orderId}", orderId.toString()).
                    replace("{orderDetailId}", orderDetailId.toString());
            REST_TEMPLATE.put(baseWebServiceUrl + updateOrderDetailUrl, orderDetailDTO,
                    OrderDetailDTO.class, new HashMap<String, String>()
            );
        } catch (Throwable e) {
            throw new RestException(e);
        }
    }

    @Override
    public void deleteOrderDetail(Long bookId, Long orderId, Long orderDetailId) throws RestException {
        try {
            String deleteOrderDetailUrl = DELETE_ORDERDETAIL_URL.replace("{bookId}", bookId.toString()).
                    replace("{orderId}", orderId.toString()).
                    replace("{orderDetailId}", orderDetailId.toString());
            REST_TEMPLATE.getForEntity(baseWebServiceUrl + deleteOrderDetailUrl,
                    OrderDetailDTO.class, new HashMap<String, String>()
            );
        } catch (Throwable e) {
            throw new RestException(e);
        }
    }
}
