package lv.javaguru.ee.warehouse.core.services;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;
import java.util.Set;
import lv.javaguru.ee.warehouse.core.command.CreateOutgoingOrderCommand;
import lv.javaguru.ee.warehouse.core.command.CreateOutgoingOrderCommandResult;
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
public class CreateOutgoingOrderCommandHandler implements 
        DomainCommandHandler<CreateOutgoingOrderCommand, CreateOutgoingOrderCommandResult>{

    @Autowired
    private OrderDAO orderDAO;
    
    @Autowired
    private ProductDAO productDAO;
    
    @Autowired
    private WarehouseDAO warehouseDAO;
    
    @Override
    public CreateOutgoingOrderCommandResult execute(CreateOutgoingOrderCommand command) {
        
        validate(command);
        
        Warehouse warehouse = command.getWarehouse();
        warehouse = warehouseDAO.getByTitle(warehouse.getTitle());
        requireNonNull(warehouse, "OutgoingOrder warehouse not found");
        
        Order order = createOutgoingOrderFromCommand(command, warehouse);
        
        Set<Product> products = command.getProducts(); 
        for (Product product : products) {
            product = productDAO.getByCode(product.getCode());
            requireNonNull(product, "OutgoingOrder product not found");
            requireNonNull(product.getCode(), "OutgoingOrder product code can not be empty");
            order.addProduct(product);
        }
                  
        orderDAO.create(order);
        
        //todo sdelatj spisanije tovara
        
        return new CreateOutgoingOrderCommandResult(order);
    }

    @Override
    public Class<CreateOutgoingOrderCommand> getCommandType() {
        return CreateOutgoingOrderCommand.class;
    }

    @Override
    public void validate(CreateOutgoingOrderCommand command) {
        requireNonNull(command, "OutgoingOrder can not be empty");
        requireNonNull(command.getProducts(), "OutgoingOrder product can not be empty");
        checkArgument(!command.getProducts().isEmpty(), "OutgoingOrder products can not be empty");
        requireNonNull(command.getWarehouse(), "OutgoingOrder warehouse can not be empty");
        requireNonNull(command.getWarehouse().getTitle(), "OutgoingOrder warehouse title can not be empty");
        requireNonNull(command.getAmount(), "OutgoingOrder amount can not be empty");
        checkArgument(command.getAmount() != 0, "OutgoingOrder amount can not be 0");        
        requireNonNull(command.getQuantity(), "OutgoingOrder quantity can not be empty");
        checkArgument(command.getAmount() != 0, "OutgoingOrder quantity can not be 0");
    }

    private Order createOutgoingOrderFromCommand(CreateOutgoingOrderCommand command, Warehouse warehouse) {
        Order order = new Order();
        order.setDirection(Order.Direction.OUTGOING);        
        order.setWarehouse(warehouse);
        order.setAmount(command.getAmount());
        order.setQuantity(command.getQuantity());                
        return order;
    }
    
}
