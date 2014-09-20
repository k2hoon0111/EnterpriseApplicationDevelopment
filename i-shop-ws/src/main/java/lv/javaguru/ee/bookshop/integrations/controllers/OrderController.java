package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.core.CommandExecutor;
import lv.javaguru.ee.bookshop.core.commands.CreateOrderCommand;
import lv.javaguru.ee.bookshop.core.commands.CreateOrderResult;
import lv.javaguru.ee.bookshop.core.domain.Order;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by MumboJumbo on 20/09/14.
 */
@Controller
public class OrderController {

    @Autowired
    private CommandExecutor commandExecutor;

    @RequestMapping(method = RequestMethod.POST, value = "/rest/order/")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        CreateOrderCommand command = createOrderCommand(orderDTO);
        CreateOrderResult result = commandExecutor.execute(command);

        Order order = result.getOrder();
        OrderDTO resultDTO = createOrderDTO(order);

        return new ResponseEntity<OrderDTO>(resultDTO, HttpStatus.CREATED);
    }

    private OrderDTO createOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();

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

    private CreateOrderCommand createOrderCommand(OrderDTO orderDTO) {
        return new CreateOrderCommand(
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