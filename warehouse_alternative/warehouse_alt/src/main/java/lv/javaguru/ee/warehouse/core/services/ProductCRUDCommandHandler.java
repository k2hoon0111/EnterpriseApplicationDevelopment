package lv.javaguru.ee.warehouse.core.services;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import lv.javaguru.ee.warehouse.core.command.ProductCRUDCommand;
import lv.javaguru.ee.warehouse.core.command.ProductCommandResult;
import lv.javaguru.ee.warehouse.core.database.ProductDAO;
import lv.javaguru.ee.warehouse.core.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author dell
 */
@Component
public class ProductCRUDCommandHandler implements DomainCommandHandler<ProductCRUDCommand, ProductCommandResult> {

    @Autowired
    private ProductDAO productDao;

    @Override
    public Class<ProductCRUDCommand> getCommandType() {
        return ProductCRUDCommand.class;
    }

    @Override
    public ProductCommandResult execute(ProductCRUDCommand command) {

        validate(command);

        Product product = null;
        switch (command.getAction()) {
            case GET:
                product = productDao.getByCode(command.getCode());
                requireNonNull(product, format("Product with code=%s not found", command.getCode()));
                break;
            case CREATE:
                validateCreate(command);
                product = createProductFromCommand(command);
                productDao.create(product);
                break;
            case UPDATE:
                product = productDao.getByCode(command.getCode());
                requireNonNull(product, format("Product with code=%s not found", command.getCode()));
                updateProductFromCommand(command, product);
                productDao.update(product);
                break;
            case DELETE:
                product = productDao.getByCode(command.getCode());
                requireNonNull(product, format("Product with code=%s not found", command.getCode()));
                productDao.delete(product);
                break;
        }

        return new ProductCommandResult(product);
    }

    @Override
    public void validate(ProductCRUDCommand command) {
        requireNonNull(command, "ProductCommand can not be empty");
        requireNonNull(command.getAction(), "ProductCommand action can not be empty");
        requireNonNull(command.getCode(), "Product code is empty");
    }

    private void validateCreate(ProductCRUDCommand command) {
        requireNonNull(command.getTitle(), "Product title can not be empty");
    }

    private Product createProductFromCommand(ProductCRUDCommand command) {
        Product product = new Product();
        updateProductFromCommand(command, product);
        return product;
    }
    
    private Product updateProductFromCommand(ProductCRUDCommand command, Product product) {
        product.setCode(command.getCode());
        product.setDescription(command.getDescription());
        product.setTitle(command.getTitle());
        return product;
    }
    
}
