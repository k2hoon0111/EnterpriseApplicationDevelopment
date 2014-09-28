package lv.javaguru.ee.warehouse.core.command;

import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;

/**
 *
 * @author dell
 */
public class CreateOutgoingOrderCommand implements DomainCommand<CreateOutgoingOrderCommandResult> {
    
    private Product product;

    private Warehouse warehouse;

    private Integer quantity;

    private Integer amount;

    public CreateOutgoingOrderCommand() {
    }

    public CreateOutgoingOrderCommand(Product product, Warehouse warehouse, Integer quantity, Integer amount) {
        this.product = product;
        this.warehouse = warehouse;
        this.quantity = quantity;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
            
}
