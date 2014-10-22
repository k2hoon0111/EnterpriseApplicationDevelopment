package lv.javaguru.ee.warehouse.core.command;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;

/**
 *
 * @author dell
 */
public class CreateIncomingOrderCommand implements DomainCommand<CreateIncomingOrderCommandResult> {

    private Set<Product> products = new HashSet<>();

    private Warehouse warehouse;

    private Integer quantity;

    private Integer amount;

    public CreateIncomingOrderCommand(Product product, Warehouse warehouse, Integer quantity, Integer amount) {
        this.products.add(product);
        this.warehouse = warehouse;
        this.quantity = quantity;
        this.amount = amount;
    }
        
    public CreateIncomingOrderCommand() {
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void addProduct(Product... product) {
        if (product == null || product.length==0) {
            throw new RuntimeException("Can not add null to the order products");
        }
        this.products.addAll(Arrays.asList(product));
    }
    
    public void setProducts(Set<Product> products) {
        this.products = products;
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
