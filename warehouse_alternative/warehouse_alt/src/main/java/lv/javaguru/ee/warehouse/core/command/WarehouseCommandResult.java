package lv.javaguru.ee.warehouse.core.command;

import lv.javaguru.ee.warehouse.core.domain.Warehouse;

/**
 *
 * @author dell
 */
public class WarehouseCommandResult implements DomainCommandResult {
    
    private Warehouse warehouse;

    public WarehouseCommandResult(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }
            
}
