package lv.javaguru.ee.warehouse.core.services;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import lv.javaguru.ee.warehouse.core.command.ProductPropertiesCRUDCommand;
import lv.javaguru.ee.warehouse.core.command.ProductPropertiesCommandResult;
import lv.javaguru.ee.warehouse.core.database.ProductDAO;
import lv.javaguru.ee.warehouse.core.database.ProductPropertiesDAO;
import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.ProductProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class ProductPropertiesCRUDCommandHandler implements
        DomainCommandHandler<ProductPropertiesCRUDCommand, ProductPropertiesCommandResult> {

    @Autowired
    private ProductPropertiesDAO prodPropDao;

    @Autowired
    private ProductDAO productDao;

    @Override
    public ProductPropertiesCommandResult execute(ProductPropertiesCRUDCommand command) {

        validate(command);

        Product product = productDao.getByCode(command.getProduct().getCode());
        requireNonNull(product, format("Product with code=%s not found", product.getCode()));
        
        ProductProperties prodProp = null;
        switch (command.getAction()) {
            case GET:
                prodProp = getByProductCodeAndName(product, command.getName());
                break;
            case CREATE:
                validateCreate(command);
                prodProp = createProdPropFromCommand(command);                
                prodProp.setProduct(product);
                prodPropDao.create(prodProp);
                break;
            case UPDATE:
                prodProp = getByProductCodeAndName(product, command.getName());
                prodProp.setValue(command.getValue());
                prodPropDao.update(prodProp);
                break;
            case DELETE:                
                prodProp = getByProductCodeAndName(product, command.getName());
                prodProp.getProduct().getProductProperties().remove(command.getName());
                prodPropDao.delete(prodProp);
                break;
        }

        return new ProductPropertiesCommandResult(prodProp);
    }

    @Override
    public Class<ProductPropertiesCRUDCommand> getCommandType() {
        return ProductPropertiesCRUDCommand.class;
    }

    @Override
    public void validate(ProductPropertiesCRUDCommand command) {
        requireNonNull(command, "ProductPropertiesCommand can not be empty");
        requireNonNull(command.getProduct(), "ProductPropertiesCommand product id can not be empty");
        requireNonNull(command.getProduct().getCode(), "ProductPropertiesCommand product id can not be empty");
        requireNonNull(command.getName(), "ProductProperty name is empty");
    }

    private void validateCreate(ProductPropertiesCRUDCommand command) {
        requireNonNull(command.getValue(), "ProductProperty value is empty");
    }

    private ProductProperties createProdPropFromCommand(ProductPropertiesCRUDCommand command) {
        ProductProperties prodProp = new ProductProperties();
        prodProp.setName(command.getName());
        prodProp.setValue(command.getValue());        
        return prodProp;
    }

    private ProductProperties getByProductCodeAndName(Product product, String name) {
        ProductProperties prodProp = prodPropDao.getByProductAndPropertyName(product, name);        
        String msg = format("ProductProperty for product=%s with name=%s not found",
                            product.getCode(), name);
        requireNonNull(prodProp, msg);
        return prodProp;
    }

}
