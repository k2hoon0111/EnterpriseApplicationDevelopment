package lv.javaguru.ee.warehouse.core.command;

import lv.javaguru.ee.warehouse.core.domain.Product;

/**
 *
 * @author dell
 */
public class ProductCommandResult implements DomainCommandResult {
 
    private Product product;
  
    public ProductCommandResult(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
            
}
