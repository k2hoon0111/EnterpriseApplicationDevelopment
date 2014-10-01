package lv.javaguru.ee.warehouse.core.command;

import lv.javaguru.ee.warehouse.core.domain.Product;

/**
 *
 * @author dell
 */
public class ProductPropertiesCRUDCommand implements DomainCRUDCommand<ProductPropertiesCRUDCommandResult>{

    private Action action;
    
    private Product product;
    
    private String name;
    
    private String value;

    public ProductPropertiesCRUDCommand() {
    }
        
    public ProductPropertiesCRUDCommand(Action action, Product product, String name, String value) {
        this.action = action;
        this.product = product;
        this.name = name;
        this.value = value;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
