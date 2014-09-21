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

public class CreateOrderDetailTest extends EmbeddedJettyTest {

    @Test
    public void testCreateOrderDetail() {

        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        orderDetailDTO.setBookId(Long.valueOf(48));
        orderDetailDTO.setOrderId(Long.valueOf(13));
        orderDetailDTO.setQuantity(5);

        ResponseEntity<OrderDetailDTO> createOrderDetailResponse
                = RestFixture.createOrderDetail(orderDetailDTO);
        OrderDetailDTO createOrderDetailDTO = createOrderDetailResponse.getBody();

        MatcherAssert.assertThat(createOrderDetailDTO.getOrderDetailId(), is(notNullValue()));
    }

}