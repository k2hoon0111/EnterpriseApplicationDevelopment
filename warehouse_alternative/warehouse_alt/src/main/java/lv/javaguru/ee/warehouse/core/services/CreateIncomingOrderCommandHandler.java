package lv.javaguru.ee.warehouse.core.services;

import static com.google.common.base.Preconditions.*;
import static java.util.Objects.requireNonNull;
import java.util.Set;
import lv.javaguru.ee.warehouse.core.command.CreateIncomingOrderCommand;
import lv.javaguru.ee.warehouse.core.command.CreateIncomingOrderCommandResult;
import lv.javaguru.ee.warehouse.core.database.OrderDAO;
import lv.javaguru.ee.warehouse.core.database.ProductDAO;
import lv.javaguru.ee.warehouse.core.database.WarehouseDAO;
import lv.javaguru.ee.warehouse.core.domain.Order;
import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 * @author dell
 */
@Component
public class CreateIncomingOrderCommandHandler implements 
        DomainCommandHandler<CreateIncomingOrderCommand, CreateIncomingOrderCommandResult>{

    @Autowired
    private OrderDAO orderDAO;
    
    @Autowired
    private ProductDAO productDAO;
    
    @Autowired
    private WarehouseDAO warehouseDAO;
    
    @Override
    public CreateIncomingOrderCommandResult execute(CreateIncomingOrderCommand command) {
        
        validate(command);
        
        Warehouse warehouse = command.getWarehouse();
        warehouse = warehouseDAO.getByTitle(warehouse.getTitle());
        requireNonNull(warehouse, "IncomingOrder warehouse not found");
        
        Order order = createIncomingOrderFromCommand(command, warehouse);
        
        Set<Product> products = command.getProducts();           
        for (Product product : products) {
            product = productDAO.getByCode(product.getCode());
            requireNonNull(product, "IncomingOrder product not found");
            requireNonNull(product.getCode(), "IncomingOrder product code can not be empty");
            order.addProduct(product);
        }
                
        orderDAO.create(order);
        
        //todo uveli4itj ostatki tovara na sklade
        
        return new CreateIncomingOrderCommandResult(order);
    }

    @Override
    public Class<CreateIncomingOrderCommand> getCommandType() {
        return CreateIncomingOrderCommand.class;
    }

    @Override
    public void validate(CreateIncomingOrderCommand command) {
        requireNonNull(command, "IncomingOrder can not be empty");
        requireNonNull(command.getProducts(), "IncomingOrder products can not be empty");
        checkArgument(!command.getProducts().isEmpty(), "IncomingOrder products can not be empty");        
        requireNonNull(command.getWarehouse(), "IncomingOrder warehouse can not be empty");
        requireNonNull(command.getWarehouse().getTitle(), "IncomingOrder warehouse title can not be empty");
        requireNonNull(command.getAmount(), "IncomingOrder amount can not be empty");
        checkArgument(command.getAmount() != 0, "IncomingOrder amount can not be 0");        
        requireNonNull(command.getQuantity(), "IncomingOrder quantity can not be empty");
        checkArgument(command.getAmount() != 0, "IncomingOrder quantity can not be 0");
    }

    private Order createIncomingOrderFromCommand(CreateIncomingOrderCommand command, Warehouse warehouse) {           
        Order order = new Order();
        order.setDirection(Order.Direction.INCOMING);        
        order.setWarehouse(warehouse);
        order.setAmount(command.getAmount());
        order.setQuantity(command.getQuantity());                
        return order;
    }
       
}
