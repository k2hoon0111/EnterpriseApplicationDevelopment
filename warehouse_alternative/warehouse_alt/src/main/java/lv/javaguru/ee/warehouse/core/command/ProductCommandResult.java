package lv.javaguru.ee.warehouse.core.command;

import lv.javaguru.ee.warehouse.core.domain.Product;

/**
 *
 * @author dell
 */
public class ProductCommandResult implements DomainCommandResult<Product> {
 
    private Product product;
  
    public ProductCommandResult(Product product) {
        this.product = product;
    }

    @Override
    public Product getResult() {
        return product;
    }
            
}
