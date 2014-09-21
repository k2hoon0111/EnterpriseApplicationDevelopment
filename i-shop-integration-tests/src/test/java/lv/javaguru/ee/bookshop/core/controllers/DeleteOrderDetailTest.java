package lv.javaguru.ee.bookshop.core.controllers;

import junit.framework.TestCase;
import lv.javaguru.ee.bookshop.core.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.core.jetty.EmbeddedJettyTest;
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
        orderDetailDTO.setBookId(Long.valueOf(45));
        orderDetailDTO.setOrderId(Long.valueOf(13));
        orderDetailDTO.setQuantity(5);

        ResponseEntity<OrderDetailDTO> createOrderDetailResponse
                = RestFixture.createOrderDetail(orderDetailDTO);
        OrderDetailDTO createdOrderDetailDTO = createOrderDetailResponse.getBody();

        Long orderDetailId = createdOrderDetailDTO.getOrderDetailId();

        RestFixture.deleteOrderDetail(orderDetailId);

        try {
            RestFixture.getOrderDetail(Long.valueOf(orderDetailId));
        } catch (HttpClientErrorException e) {
            TestCase.assertEquals("OrderDetail id not valid", e.getResponseBodyAsString());
            TestCase.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
        }

    }

}

