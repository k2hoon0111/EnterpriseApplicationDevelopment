package lv.javaguru.ee.bookshop.core.controllers;

import lv.javaguru.ee.bookshop.core.controllers.fixtures.RestFixture;
import lv.javaguru.ee.bookshop.core.jetty.EmbeddedJettyTest;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDetailDTO;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Created by MumboJumbo on 21/09/14.
 */

public class UpdateOrderDetailTest extends EmbeddedJettyTest {

    @Test
    public void testUpdateOrderDetail() {
        // Create orderDetail
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        orderDetailDTO.setBookId(Long.valueOf(48));
        orderDetailDTO.setOrderId(Long.valueOf(13));
        orderDetailDTO.setQuantity(5);

        ResponseEntity<OrderDetailDTO> createOrderDetailResponse
                = RestFixture.createOrderDetail(orderDetailDTO);
        OrderDetailDTO createdOrderDetailDTO = createOrderDetailResponse.getBody();

        MatcherAssert.assertThat(createdOrderDetailDTO.getOrderDetailId(), is(notNullValue()));
        MatcherAssert.assertThat(createdOrderDetailDTO.getQuantity(), is(5));

        // Update orderDetail
        OrderDetailDTO orderDetailDTOForUpdate = new OrderDetailDTO();
        orderDetailDTOForUpdate.setOrderDetailId(createdOrderDetailDTO.getOrderDetailId());

        orderDetailDTOForUpdate.setBookId(Long.valueOf(48));
        orderDetailDTOForUpdate.setOrderId(Long.valueOf(13));
        orderDetailDTOForUpdate.setQuantity(8);

        RestFixture.updateOrderDetail(orderDetailDTOForUpdate);


//        ResponseEntity<OrderDetailDTO> getOrderDetailResponse
//                = RestFixture.getOrderDetail(orderDetailDTOForUpdate.getOrderDetailId());
//
//        OrderDetailDTO getOrderDetailDTO = getOrderDetailResponse.getBody();
//        MatcherAssert.assertThat(getOrderDetailDTO.getOrderDetailId(), CoreMatchers.is(orderDetailDTOForUpdate.getOrderDetailId()));
//        MatcherAssert.assertThat(getOrderDetailDTO.getQuantity(), is(6));
    }

}
