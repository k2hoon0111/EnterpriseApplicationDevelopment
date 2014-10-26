package lv.javaguru.ee.warehouse.integrations.controllers;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.Set;
import lv.javaguru.ee.warehouse.core.CommandExecutor;
import lv.javaguru.ee.warehouse.core.command.CreateIncomingOrderCommand;
import lv.javaguru.ee.warehouse.core.command.CreateIncomingOrderCommandResult;
import lv.javaguru.ee.warehouse.core.command.CreateOutgoingOrderCommand;
import lv.javaguru.ee.warehouse.core.command.CreateOutgoingOrderCommandResult;
import lv.javaguru.ee.warehouse.core.domain.Order;
import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;
import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.OrderDTO;
import lv.javaguru.ee.warehouse.integrations.resourses.OrderResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dell
 */
@Api(value = "Order", description = "Warehouse incoming/outgoing order API")
@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class OrderController implements OrderResource {
    
    @Autowired
    private CommandExecutor commandExecutor;

    @Override
    @ApiOperation(value = "Create incoming order", notes = "Create incoming order (increase product qty in warehouse)")
    @ApiResponses({@ApiResponse(code = 422, message = "Unprocessable Entity")})
    @RequestMapping(value = CREATE_IN_ORDER_URL, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createIncomingOrder(@RequestBody 
            @ApiParam(name = "order", required = true, value = "incoming order to create")
            OrderDTO orderDTO) throws RestException {
        CreateIncomingOrderCommand command = createIncomingOrderCommand(orderDTO);        
        CreateIncomingOrderCommandResult result = commandExecutor.execute(command);        
        return createOrderDTO(result.getResult());
    }

    @Override
    @ApiOperation(value = "Create outgoing order", notes = "Create outgoing order (decrease product qty in warehouse)")
    @ApiResponses({@ApiResponse(code = 422, message = "Unprocessable Entity")})
    @RequestMapping(value = CREATE_OUT_ORDER_URL, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOutgoingOrder(@RequestBody 
            @ApiParam(name = "order", required = true, value = "outgoing order to create")
            OrderDTO orderDTO) throws RestException {
        CreateOutgoingOrderCommand command = createOutgoingOrderCommand(orderDTO);        
        CreateOutgoingOrderCommandResult result = commandExecutor.execute(command);        
        return createOrderDTO(result.getResult());
    }

    private CreateIncomingOrderCommand createIncomingOrderCommand(OrderDTO orderDTO) {        
        CreateIncomingOrderCommand command = new CreateIncomingOrderCommand();
        Warehouse warehouse = new Warehouse();
        warehouse.setTitle(orderDTO.getWarehouseCode());
        command.setWarehouse(warehouse);
        
        Set<Long> productCodes = orderDTO.getProductCodess();
        for (Long productCode : productCodes) {
            Product product = new Product();
            product.setCode(productCode);
            command.addProduct(product);
        }
               
        command.setAmount(orderDTO.getAmount());
        command.setQuantity(orderDTO.getQuantity());        
        return command;        
    }

    private CreateOutgoingOrderCommand createOutgoingOrderCommand(OrderDTO orderDTO) {
        CreateOutgoingOrderCommand command = new CreateOutgoingOrderCommand();
        Warehouse warehouse = new Warehouse();
        warehouse.setTitle(orderDTO.getWarehouseCode());
        command.setWarehouse(warehouse);
        
        Set<Long> productCodes = orderDTO.getProductCodess();
        for (Long productCode : productCodes) {
            Product product = new Product();
            product.setCode(productCode);
            command.addProduct(product);
        }
                
        command.setAmount(orderDTO.getAmount());
        command.setQuantity(orderDTO.getQuantity());        
        return command;   
    }

    private OrderDTO createOrderDTO(Order result) {        
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setWarehouseCode(result.getWarehouse().getTitle());
            
        Set<Product> productes = result.getProducts();
        for (Product product : productes) {
            orderDTO.addProductCode(product.getCode());
        }
        
        orderDTO.setAmount(result.getAmount());
        orderDTO.setQuantity(result.getQuantity());
        return orderDTO;
    }
    
}
