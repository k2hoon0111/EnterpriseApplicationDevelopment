package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.core.CommandExecutor;
import lv.javaguru.ee.bookshop.core.commands.*;
import lv.javaguru.ee.bookshop.core.domain.OrderDetail;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by MumboJumbo on 20/09/14.
 */
@RestController
public class OrderDetailController {

    @Autowired
    private CommandExecutor commandExecutor;

    @RequestMapping(method = RequestMethod.POST, value = "/rest/book/{bookId}/order/{orderId}/orderdetail")
    public ResponseEntity<OrderDetailDTO> createOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        CreateOrderDetailCommand command = createOrderDetailCommand(orderDetailDTO);
        CreateOrderDetailResult result = commandExecutor.execute(command);

        OrderDetail orderDetail = result.getOrderDetail();
        OrderDetailDTO resultDTO = createOrderDetailDTO(orderDetail);

        return new ResponseEntity<OrderDetailDTO>(resultDTO, HttpStatus.CREATED);
    }

    private OrderDetailDTO createOrderDetailDTO(OrderDetail orderDetail) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setOrderDetailId(orderDetail.getOrderDetailId());
        orderDetailDTO.setBookId(orderDetail.getBook().getBookId());
        orderDetailDTO.setOrderId(orderDetail.getOrderId());
        orderDetailDTO.setQuantity(orderDetail.getQuantity());

        return orderDetailDTO;
    }

    private CreateOrderDetailCommand createOrderDetailCommand(OrderDetailDTO orderDetailDTO) {
        return new CreateOrderDetailCommand(
                orderDetailDTO.getBookId(),
                orderDetailDTO.getOrderId(),
                orderDetailDTO.getQuantity()
        );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rest/book/{bookId}/order/{orderId}/orderdetail{orderDetailId}")
    public ResponseEntity<OrderDetailDTO> getOrderDetail(@PathVariable Long orderDetailId) {
        GetOrderDetailCommand command = new GetOrderDetailCommand(orderDetailId);
        GetOrderDetailResult result = commandExecutor.execute(command);

        OrderDetail orderDetail = result.getOrderDetail();
        OrderDetailDTO orderDetailDTO = createOrderDetailDTO(orderDetail);
        return new ResponseEntity<OrderDetailDTO>(orderDetailDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/rest/orderdetail/{orderDetailId}")
    public ResponseEntity deleteOrderDetail(@PathVariable Long orderDetailId) {
        DeleteOrderDetailCommand command = new DeleteOrderDetailCommand(orderDetailId);
        DeleteOrderDetailResult result = commandExecutor.execute(command);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rest/orderdetail/{orderDetailId}")
    public ResponseEntity<OrderDetailDTO> UpdateOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        UpdateOrderDetailCommand command = updateOrderDetailCommand(orderDetailDTO);
        UpdateOrderDetailResult result = commandExecutor.execute(command);

        return new ResponseEntity<OrderDetailDTO>(HttpStatus.OK);
    }

    private UpdateOrderDetailCommand updateOrderDetailCommand(OrderDetailDTO orderDetailDTO) {
        return new UpdateOrderDetailCommand(
                orderDetailDTO.getOrderDetailId(),
                orderDetailDTO.getBookId(),
                orderDetailDTO.getOrderId(),
                orderDetailDTO.getQuantity()
        );
    }
}
