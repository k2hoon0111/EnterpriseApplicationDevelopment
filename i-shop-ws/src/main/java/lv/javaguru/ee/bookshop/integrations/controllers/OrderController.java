package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.core.CommandExecutor;
import lv.javaguru.ee.bookshop.core.commands.*;
import lv.javaguru.ee.bookshop.core.domain.Order;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by MumboJumbo on 20/09/14.
 */
@RestController
public class OrderController {

    @Autowired
    private CommandExecutor commandExecutor;

    @RequestMapping(method = RequestMethod.POST, value = "/rest/account/{accountId}/order")
    public ResponseEntity<OrderDTO> createOrder(@PathVariable Long accountId,
                                                @RequestBody OrderDTO orderDTO) {
        CreateOrderCommand command = createOrderCommand(accountId, orderDTO);
        CreateOrderResult result = commandExecutor.execute(command);

        Order order = result.getOrder();
        OrderDTO resultDTO = createOrderDTO(order);

        return new ResponseEntity<OrderDTO>(resultDTO, HttpStatus.CREATED);
    }

    private OrderDTO createOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderId(order.getOrderId());

        orderDTO.setAccountId(order.getAccount().getAccountId());

        orderDTO.setShippingStreet(order.getShippingAddress().getStreet());
        orderDTO.setShippingHouseNumber(order.getShippingAddress().getHouseNumber());
        orderDTO.setShippingBoxNumber(order.getShippingAddress().getBoxNumber());
        orderDTO.setShippingCity(order.getShippingAddress().getCity());
        orderDTO.setShippingPostalCode(order.getShippingAddress().getPostalCode());
        orderDTO.setShippingCountry(order.getShippingAddress().getCountry());
        orderDTO.setBillingStreet(order.getBillingAddress().getStreet());
        orderDTO.setBillingHouseNumber(order.getBillingAddress().getHouseNumber());
        orderDTO.setBillingBoxNumber(order.getBillingAddress().getBoxNumber());
        orderDTO.setBillingCity(order.getBillingAddress().getCity());
        orderDTO.setBillingoPostalCode(order.getBillingAddress().getPostalCode());
        orderDTO.setBillingCountry(order.getBillingAddress().getCountry());
        orderDTO.setBillingSameAsShipping(order.isBillingSameAsShipping());
        orderDTO.setDeliveryDate(order.getDeliveryDate());
        orderDTO.setOrderDate(order.getOrderDate());

        return orderDTO;
    }

    private CreateOrderCommand createOrderCommand(Long accountId, OrderDTO orderDTO) {
        return new CreateOrderCommand(
                accountId,
                orderDTO.getShippingStreet(),
                orderDTO.getShippingHouseNumber(),
                orderDTO.getShippingBoxNumber(),
                orderDTO.getShippingCity(),
                orderDTO.getShippingPostalCode(),
                orderDTO.getShippingCountry(),
                orderDTO.getBillingStreet(),
                orderDTO.getBillingHouseNumber(),
                orderDTO.getBillingBoxNumber(),
                orderDTO.getBillingCity(),
                orderDTO.getBillingoPostalCode(),
                orderDTO.getBillingCountry(),
                orderDTO.isBillingSameAsShipping(),
                orderDTO.getDeliveryDate(),
                orderDTO.getOrderDate()
        );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rest/account/{accountId}/order/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long accountId,
                                             @PathVariable Long orderId) {
        GetOrderCommand command = new GetOrderCommand(accountId, orderId);
        GetOrderResult result = commandExecutor.execute(command);
        Order order = result.getOrder();
        OrderDTO orderDTO = createOrderDTO(order);
        return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/rest/order/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable Long orderId) {
        DeleteOrderCommand command = new DeleteOrderCommand(orderId);
        DeleteOrderResult result = commandExecutor.execute(command);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rest/order/{orderId}")
    public ResponseEntity<OrderDTO> UpdateOrder(@RequestBody OrderDTO orderDTO) {
        UpdateOrderCommand command = updateOrderCommand(orderDTO);
        UpdateOrderResult result = commandExecutor.execute(command);

        return new ResponseEntity<OrderDTO>(HttpStatus.OK);
    }

    private UpdateOrderCommand updateOrderCommand(OrderDTO orderDTO) {
        return new UpdateOrderCommand(
                orderDTO.getOrderId(),
                orderDTO.getAccountId(),
                orderDTO.getShippingStreet(),
                orderDTO.getShippingHouseNumber(),
                orderDTO.getShippingBoxNumber(),
                orderDTO.getShippingCity(),
                orderDTO.getShippingPostalCode(),
                orderDTO.getShippingCountry(),
                orderDTO.getBillingStreet(),
                orderDTO.getBillingHouseNumber(),
                orderDTO.getBillingBoxNumber(),
                orderDTO.getBillingCity(),
                orderDTO.getBillingoPostalCode(),
                orderDTO.getBillingCountry(),
                orderDTO.isBillingSameAsShipping(),
                orderDTO.getDeliveryDate(),
                orderDTO.getOrderDate()
        );
    }
}