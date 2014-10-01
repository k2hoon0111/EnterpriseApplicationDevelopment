package lv.javaguru.ee.warehouse.core.services;

import static com.google.common.base.Strings.emptyToNull;
import static java.lang.String.format;
import static java.util.Objects.*;
import lv.javaguru.ee.warehouse.core.command.WarehouseCRUDCommand;
import lv.javaguru.ee.warehouse.core.command.WarehouseCommandResult;
import lv.javaguru.ee.warehouse.core.database.WarehouseDAO;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class WarehouseCRUDCommandHandler implements DomainCommandHandler<WarehouseCRUDCommand, WarehouseCommandResult>{

    @Autowired
    private WarehouseDAO warehouseDao;
    
    @Override
    public WarehouseCommandResult execute(WarehouseCRUDCommand command) {
        
        validate(command);
        Warehouse warehouse = null;
        
        switch (command.getAction()) {
            case GET:
                warehouse = warehouseDao.getByTitle(command.getTitle());
                requireNonNull(warehouse, format("Warehouse with title=%s not found", command.getTitle()));
                break;
            case CREATE:                
                warehouse = createWarehouseFromCommand(command);
                warehouseDao.create(warehouse);
                break;
            case UPDATE:
                warehouse = warehouseDao.getByTitle(command.getTitle());
                requireNonNull(warehouse, format("Warehouse with title=%s not found", command.getTitle()));
                updateProductFromCommand(command, warehouse);
                warehouseDao.update(warehouse);
                break;
            case DELETE:
                warehouse = warehouseDao.getByTitle(command.getTitle());
                requireNonNull(warehouse, format("Warehouse with title=%s not found", command.getTitle()));
                warehouseDao.delete(warehouse);
                break;
        }        
        return new WarehouseCommandResult(warehouse);
    }

    @Override
    public Class<WarehouseCRUDCommand> getCommandType() {
        return WarehouseCRUDCommand.class;
    }

    @Override
    public void validate(WarehouseCRUDCommand command) {
        requireNonNull(command, "WarehouseCommand can not be empty");
        requireNonNull(command.getAction(), "WarehouseCommand action can not be empty");
        requireNonNull(emptyToNull(command.getTitle()), "Warehouse title can not be empty");
    }

    private Warehouse createWarehouseFromCommand(WarehouseCRUDCommand command) {        
        Warehouse warehouse = new Warehouse();
        updateProductFromCommand(command, warehouse);        
        return warehouse;
    }

    private void updateProductFromCommand(WarehouseCRUDCommand command, Warehouse warehouse) {        
        warehouse.setTitle(command.getTitle());
        warehouse.setDescription(command.getDescription());
        warehouse.setAddress(command.getAddress());        
    }
    
}
