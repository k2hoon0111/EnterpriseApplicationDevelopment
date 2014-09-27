package lv.javaguru.ee.warehouse.core.command;

import lv.javaguru.ee.warehouse.core.domain.WarehouseAddress;

/**
 *
 * @author dell
 */
public class WarehouseCRUDCommand implements DomainCRUDCommand<WarehouseCommandResult> {
    
    private Action action;
    
    private String title;
        
    private String description;
    
    private WarehouseAddress address;

    public WarehouseCRUDCommand(String title, String description, WarehouseAddress address) {
        this.title = title;
        this.description = description;
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WarehouseAddress getAddress() {
        return address;
    }

    public void setAddress(WarehouseAddress address) {
        this.address = address;
    }

    @Override
    public Action getAction() {
        return this.action;
    }

    @Override
    public void setAction(Action action) {
        this.action = action;
    }
            
}
