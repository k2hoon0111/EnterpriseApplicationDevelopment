package lv.javaguru.ee.warehouse.integrations.controllers;

import lv.javaguru.ee.warehouse.core.CommandExecutor;
import lv.javaguru.ee.warehouse.core.command.Action;
import lv.javaguru.ee.warehouse.core.command.ProductPropertiesCRUDCommand;
import lv.javaguru.ee.warehouse.core.command.ProductPropertiesCommandResult;
import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.ProductProperties;
import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.ProductPropertiesDTO;
import lv.javaguru.ee.warehouse.integrations.resourses.ProductPropertiesResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dell
 */
@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductPropertiesController implements ProductPropertiesResource {
    
    @Autowired
    private CommandExecutor commandExecutor;

    @Override
    @RequestMapping(value = GET_PROD_PROP_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ProductPropertiesDTO getProductProperties(@PathVariable Long productCode, 
            @PathVariable String prodPropName) throws RestException {
        ProductPropertiesCRUDCommand command = createProductPropertiesCRUDCommand(productCode, prodPropName, Action.GET);        
        ProductPropertiesCommandResult result = commandExecutor.execute(command);        
        return createProductPropertiesDTO(result.getResult());
    }

    @Override
    @RequestMapping(value = CREATE_PROD_PROP_URL, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ProductPropertiesDTO createProductProperties(@PathVariable Long productCode, 
            @RequestBody ProductPropertiesDTO prodPropDTO) throws RestException {
        ProductPropertiesCRUDCommand command = createProductPropertiesCRUDCommand(productCode, prodPropDTO, Action.CREATE);        
        ProductPropertiesCommandResult result = commandExecutor.execute(command);        
        return createProductPropertiesDTO(result.getResult());
    }

    @Override
    @RequestMapping(value = UPDATE_PROD_PROP_URL, method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductPropertiesDTO updateProductProperties(@PathVariable Long productCode, 
            @PathVariable String prodPropName) throws RestException {
        ProductPropertiesCRUDCommand command = createProductPropertiesCRUDCommand(productCode, prodPropName, Action.UPDATE);        
        ProductPropertiesCommandResult result = commandExecutor.execute(command);        
        return createProductPropertiesDTO(result.getResult());
    }

    @Override
    @RequestMapping(value = DELETE_PROD_PROP_URL, method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductPropertiesDTO deleteProductProperties(@PathVariable Long productCode, 
            @PathVariable String prodPropName) throws RestException {
        ProductPropertiesCRUDCommand command = createProductPropertiesCRUDCommand(productCode, prodPropName, Action.DELETE);        
        ProductPropertiesCommandResult result = commandExecutor.execute(command);        
        return createProductPropertiesDTO(result.getResult());
    }

    private ProductPropertiesCRUDCommand createProductPropertiesCRUDCommand(Long productCode, String prodPropName, Action action) {                
        ProductPropertiesDTO prodPropDTO = new ProductPropertiesDTO();
        prodPropDTO.setName(prodPropName);
        return createProductPropertiesCRUDCommand(productCode, prodPropDTO, action);
    }
    
    private ProductPropertiesCRUDCommand createProductPropertiesCRUDCommand(Long productCode, ProductPropertiesDTO prodPropDTO, Action action) {
        ProductPropertiesCRUDCommand command = new ProductPropertiesCRUDCommand();
        command.setAction(action);
        Product product = new Product();
        product.setCode(productCode);
        command.setProduct(product);
        command.setName(prodPropDTO.getName());
        command.setValue(prodPropDTO.getValue());
        return command;
    }
    
    private ProductPropertiesDTO createProductPropertiesDTO(ProductProperties result) {
        ProductPropertiesDTO prodPropDTO = new ProductPropertiesDTO();
        prodPropDTO.setProductCode(result.getProduct().getCode());
        prodPropDTO.setName(result.getName());
        prodPropDTO.setValue(result.getValue());        
        return prodPropDTO;
    }
    
}
