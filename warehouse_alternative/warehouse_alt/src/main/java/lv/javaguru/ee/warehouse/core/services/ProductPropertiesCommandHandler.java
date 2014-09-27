package lv.javaguru.ee.warehouse.core.services;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import lv.javaguru.ee.warehouse.core.command.ProductPropertiesCRUDCommand;
import lv.javaguru.ee.warehouse.core.command.ProductPropertiesCRUDCommandResult;
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
public class ProductPropertiesCommandHandler implements
        DomainCommandHandler<ProductPropertiesCRUDCommand, ProductPropertiesCRUDCommandResult> {

    @Autowired
    private ProductPropertiesDAO prodPropDao;

    @Autowired
    private ProductDAO productDao;

    @Override
    public ProductPropertiesCRUDCommandResult execute(ProductPropertiesCRUDCommand command) {

        validate(command);

        ProductProperties prodProp = null;
        switch (command.getAction()) {
            case GET:
                prodProp = getByProductCodeAndName(command.getProduct(), command.getName());
                break;
            case CREATE:
                validateCreate(command);
                prodProp = createProductFromCommand(command);
                prodPropDao.create(prodProp);
                break;
            case UPDATE:
                prodProp = getByProductCodeAndName(command.getProduct(), command.getName());
                updateProductFromCommand(command, prodProp);
                prodPropDao.update(prodProp);
                break;
            case DELETE:                
                prodProp = getByProductCodeAndName(command.getProduct(), command.getName());
                prodPropDao.delete(prodProp);
                break;
        }

        return new ProductPropertiesCRUDCommandResult(prodProp);
    }

    @Override
    public Class<ProductPropertiesCRUDCommand> getCommandType() {
        return ProductPropertiesCRUDCommand.class;
    }

    @Override
    public void validate(ProductPropertiesCRUDCommand command) {
        requireNonNull(command, "ProductPropertiesCommand can not be empty");
        requireNonNull(command.getProduct(), "ProductPropertiesCommand product id can not be empty");
        requireNonNull(command.getProduct().getId(), "ProductPropertiesCommand product id can not be empty");
        requireNonNull(command.getName(), "ProductProperty name is empty");
    }

    private void validateCreate(ProductPropertiesCRUDCommand command) {
        requireNonNull(command.getValue(), "ProductProperty value is empty");
    }

    private ProductProperties createProductFromCommand(ProductPropertiesCRUDCommand command) {
        ProductProperties prodProp = new ProductProperties();
        updateProductFromCommand(command, prodProp);
        return prodProp;
    }

    private void updateProductFromCommand(ProductPropertiesCRUDCommand command, ProductProperties prodProp) {
        prodProp.setName(command.getName());
        prodProp.setValue(command.getValue());
        prodProp.setProduct(command.getProduct());
    }

    private ProductProperties getByProductCodeAndName(Product product, String name) {
        ProductProperties prodProp = prodPropDao.getByProductCodeAndName(product, name);        
        String msg = format("ProductProperty for product=%s with name=%s not found",
                            product.getId(), name);
        requireNonNull(prodProp, msg);
        return prodProp;
    }

}
