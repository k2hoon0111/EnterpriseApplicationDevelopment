package lv.javaguru.ee.warehouse.integrations.controllers;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
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
@Api(value = "ProductProperties", description = "Product properties CRUD API")
@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductPropertiesController implements ProductPropertiesResource {
    
    @Autowired
    private CommandExecutor commandExecutor;

    @Override
    @ApiOperation(value = "Get product property", notes = "Get product property for distinct product")
    @ApiResponses({@ApiResponse(code = 422, message = "Unprocessable Entity")})
    @RequestMapping(value = GET_PROD_PROP_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ProductPropertiesDTO getProductProperties(@PathVariable 
            @ApiParam(name = "productCode", required = true, value = "product code")
            Long productCode, 
            @PathVariable 
            @ApiParam(name = "productPropertyName", required = true, value = "product property name")        
            String prodPropName) throws RestException {
        ProductPropertiesCRUDCommand command = createProductPropertiesCRUDCommand(productCode, prodPropName, Action.GET);        
        ProductPropertiesCommandResult result = commandExecutor.execute(command);        
        return createProductPropertiesDTO(result.getResult());
    }

    @Override
    @ApiOperation(value = "Create product property", notes = "Create new product property for distinct product")
    @ApiResponses({@ApiResponse(code = 422, message = "Unprocessable Entity")})
    @RequestMapping(value = CREATE_PROD_PROP_URL, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ProductPropertiesDTO createProductProperties(@PathVariable 
            @ApiParam(name = "productCode", required = true, value = "product code to which add new product property")
            Long productCode, 
            @RequestBody 
            @ApiParam(name = "productProperty", required = true, value = "product property to add")  
            ProductPropertiesDTO prodPropDTO) throws RestException {
        ProductPropertiesCRUDCommand command = createProductPropertiesCRUDCommand(productCode, prodPropDTO, Action.CREATE);        
        ProductPropertiesCommandResult result = commandExecutor.execute(command);        
        return createProductPropertiesDTO(result.getResult());
    }

    @Override
    @ApiOperation(value = "Update product property", notes = "update product property for distinct product")
    @ApiResponses({@ApiResponse(code = 422, message = "Unprocessable Entity")})
    @RequestMapping(value = UPDATE_PROD_PROP_URL, method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductPropertiesDTO updateProductProperties(@PathVariable 
            @ApiParam(name = "productCode", required = true, value = "product code to which update product property")
            Long productCode, 
            @PathVariable 
            @ApiParam(name = "productPropertyName", required = true, value = "product property name")         
            String prodPropName,
            @RequestBody 
            @ApiParam(name = "NewProductPropertyValue", required = true, value = "new product property value")          
            ProductPropertiesDTO prodPropDTO) throws RestException {
        prodPropDTO.setName(prodPropName);
        ProductPropertiesCRUDCommand command = createProductPropertiesCRUDCommand(productCode, prodPropDTO, Action.UPDATE);        
        ProductPropertiesCommandResult result = commandExecutor.execute(command);        
        return createProductPropertiesDTO(result.getResult());
    }

    @Override
    @ApiOperation(value = "Delete product property", notes = "delete product property for distinct product")
    @ApiResponses({@ApiResponse(code = 422, message = "Unprocessable Entity")})
    @RequestMapping(value = DELETE_PROD_PROP_URL, method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductPropertiesDTO deleteProductProperties(@PathVariable 
            @ApiParam(name = "productCode", required = true, value = "product code to which delete product property")
            Long productCode, 
            @PathVariable 
            @ApiParam(name = "productPropertyName", required = true, value = "product property name") 
            String prodPropName) throws RestException {
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
