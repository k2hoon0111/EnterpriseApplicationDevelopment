package lv.javaguru.ee.warehouse.core.command;

import lv.javaguru.ee.warehouse.core.domain.ProductProperties;

/**
 *
 * @author dell
 */
public class ProductPropertiesCommandResult implements DomainCommandResult<ProductProperties> {

    private final ProductProperties productProperties;

    public ProductPropertiesCommandResult(ProductProperties productProperties) {
        this.productProperties = productProperties;
    }

    @Override
    public ProductProperties getResult() {
        return productProperties;
    }
    
}
