package lv.javaguru.ee.warehouse.core.services;

import static com.google.common.base.Preconditions.*;
import static java.util.Objects.requireNonNull;
import lv.javaguru.ee.warehouse.core.command.CreateIncomingOrderCommand;
import lv.javaguru.ee.warehouse.core.command.CreateIncomingOrderCommandResult;
import lv.javaguru.ee.warehouse.core.database.OrderDAO;
import lv.javaguru.ee.warehouse.core.database.ProductDAO;
import lv.javaguru.ee.warehouse.core.database.WarehouseDAO;
import lv.javaguru.ee.warehouse.core.domain.Order;
import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author dell
 */
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
        
        Product product = command.getProduct();        
        product = productDAO.getByCode(product.getCode());
        requireNonNull(product, "IncomingOrder product not found");
                
        Warehouse warehouse = command.getWarehouse();
        warehouse = warehouseDAO.getByTitle(warehouse.getTitle());
        requireNonNull(warehouse, "IncomingOrder warehouse not found");
        
        Order order = createIncomingOrderFromCommand(command, product, warehouse);        
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
        requireNonNull(command.getProduct(), "IncomingOrder product can not be empty");
        requireNonNull(command.getProduct().getCode(), "IncomingOrder product code can not be empty");
        requireNonNull(command.getWarehouse(), "IncomingOrder warehouse can not be empty");
        requireNonNull(command.getWarehouse().getTitle(), "IncomingOrder warehouse title can not be empty");
        requireNonNull(command.getAmount(), "IncomingOrder amount can not be empty");
        checkArgument(command.getAmount() == 0, "IncomingOrder amount can not be 0");        
        requireNonNull(command.getQuantity(), "IncomingOrder quantity can not be empty");
        checkArgument(command.getAmount() == 0, "IncomingOrder quantity can not be 0");
    }

    private Order createIncomingOrderFromCommand(CreateIncomingOrderCommand command, Product product, Warehouse warehouse) {        
        Order order = new Order();
        order.setDirection(Order.Direction.INCOMING);
        order.setProduct(product);
        order.setWarehouse(warehouse);
        order.setAmount(command.getAmount());
        order.setQuantity(command.getQuantity());                
        return order;
    }
       
}
