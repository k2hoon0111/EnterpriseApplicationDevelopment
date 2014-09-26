package lv.javaguru.ee.bookshop.integrations.controllers;

import junit.framework.TestCase;
import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.core.domain.OrderDetail;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDetailDTO;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by MumboJumbo on 21/09/14.
 */

public class DeleteOrderDetailTest extends EmbeddedJettyTest {

    @Test
    public void testDeleteOrderDetail() {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        orderDetailDTO.setBookId(Long.valueOf(53));
        orderDetailDTO.setOrderId(Long.valueOf(13));
        orderDetailDTO.setQuantity(5);

        ResponseEntity<OrderDetailDTO> createOrderDetailResponse
                = RestFixture.createOrderDetail(orderDetailDTO);
        OrderDetailDTO createOrderDetailDTO = createOrderDetailResponse.getBody();

        Long orderDetailId = createOrderDetailDTO.getOrderDetailId();

        RestFixture.deleteOrderDetail(orderDetailId);

        try {
            RestFixture.getOrderDetail(Long.valueOf(orderDetailId));
        } catch (HttpClientErrorException e) {
            TestCase.assertEquals("Entity " + OrderDetail.class.getName() + " not found by id " + orderDetailId, e.getResponseBodyAsString());
            TestCase.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
        }

    }

}

