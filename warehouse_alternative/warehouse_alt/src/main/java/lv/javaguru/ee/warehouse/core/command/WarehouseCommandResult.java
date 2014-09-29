package lv.javaguru.ee.warehouse.core.command;

import lv.javaguru.ee.warehouse.core.domain.Warehouse;

/**
 *
 * @author dell
 */
public class WarehouseCommandResult implements DomainCommandResult<Warehouse> {
    
    private final Warehouse warehouse;

    public WarehouseCommandResult(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public Warehouse getResult() {
        return warehouse;
    }
   
}
