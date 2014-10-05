package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.core.CommandExecutor;
import lv.javaguru.ee.bookshop.core.commands.*;
import lv.javaguru.ee.bookshop.core.domain.OrderDetail;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDetailDTO;
import lv.javaguru.ee.bookshop.integrations.resourses.OrderDetailResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by MumboJumbo on 20/09/14.
 */
@RestController
public class OrderDetailController implements OrderDetailResource {

    @Autowired
    private CommandExecutor commandExecutor;

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/rest/book/{bookId}/order/{orderId}/orderdetail")
    public OrderDetailDTO createOrderDetail(
            @PathVariable Long bookId,
            @PathVariable Long orderId,
            @RequestBody OrderDetailDTO orderDetailDTO) {
        CreateOrderDetailCommand command = createOrderDetailCommand(orderDetailDTO);
        CreateOrderDetailResult result = commandExecutor.execute(command);

        OrderDetail orderDetail = result.getOrderDetail();
        OrderDetailDTO resultDTO = createOrderDetailDTO(orderDetail);

        return resultDTO;
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

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/rest/book/{bookId}/order/{orderId}/orderdetail/{orderDetailId}")
    public OrderDetailDTO getOrderDetail(
            @PathVariable Long bookId,
            @PathVariable Long orderId,
            @PathVariable Long orderDetailId) {
        GetOrderDetailCommand command = new GetOrderDetailCommand(orderDetailId);
        GetOrderDetailResult result = commandExecutor.execute(command);

        OrderDetail orderDetail = result.getOrderDetail();
        OrderDetailDTO orderDetailDTO = createOrderDetailDTO(orderDetail);
        return orderDetailDTO;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value = "/rest/book/{bookId}/order/{orderId}/orderdetail/{orderDetailId}")
    public void deleteOrderDetail(
            @PathVariable Long bookId,
            @PathVariable Long orderId,
            @PathVariable Long orderDetailId) {
        DeleteOrderDetailCommand command = new DeleteOrderDetailCommand(orderDetailId);
        DeleteOrderDetailResult result = commandExecutor.execute(command);

    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, value = "/rest/book/{bookId}/order/{orderId}/orderdetail/{orderDetailId}")
    public void updateOrderDetail(
            @PathVariable Long bookId,
            @PathVariable Long orderId,
            @PathVariable Long orderDetailId,
            @RequestBody OrderDetailDTO orderDetailDTO) {
        UpdateOrderDetailCommand command = updateOrderDetailCommand(orderDetailDTO);
        UpdateOrderDetailResult result = commandExecutor.execute(command);
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
