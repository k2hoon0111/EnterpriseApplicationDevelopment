package lv.javaguru.ee.warehouse.integrations.controllers;


import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import lv.javaguru.ee.warehouse.core.CommandExecutor;
import lv.javaguru.ee.warehouse.core.command.Action;
import lv.javaguru.ee.warehouse.core.command.ProductCRUDCommand;
import lv.javaguru.ee.warehouse.core.command.ProductCommandResult;
import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.ProductDTO;
import lv.javaguru.ee.warehouse.integrations.resourses.ProductResource;
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
@Api(value = "Product", description = "Product CRUD API")
@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductController implements ProductResource {
    
    @Autowired
    private CommandExecutor commandExecutor;

    @Override    
    @ApiOperation(value = "Get product", notes = "Get product by warehouseCode")
    @ApiResponses({@ApiResponse(code = 422, message = "Unprocessable Entity")})
    @RequestMapping(value = GET_PRODUCT_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)    
    public ProductDTO getProduct(@PathVariable 
            @ApiParam(name = "productCode", required = true, value = "product code")
            Long productCode) throws RestException {        
        ProductCRUDCommand command = createProductCRUDCommand(productCode, Action.GET);        
        ProductCommandResult result = commandExecutor.execute(command);        
        return createProductDTO(result.getResult());        
    }

    @Override
    @ApiOperation(value = "Create product", notes = "Create new product")
    @ApiResponses({@ApiResponse(code = 422, message = "Unprocessable Entity")})
    @RequestMapping(value = CREATE_PRODUCT_URL, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody 
            @ApiParam(name = "product", required = true, value = "product to add")
            ProductDTO productDTO) throws RestException {        
        ProductCRUDCommand command = createProductCRUDCommand(productDTO, Action.CREATE);        
        ProductCommandResult result = commandExecutor.execute(command);        
        return createProductDTO(result.getResult());
    }

    @Override
    @ApiOperation(value = "Update product", notes = "Update product")
    @ApiResponses({@ApiResponse(code = 422, message = "Unprocessable Entity")})
    @RequestMapping(value = UPDATE_PRODUCT_URL, method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProduct(@PathVariable 
            @ApiParam(name = "productCode", required = true, value = "product code to update")
            Long productCode, 
            @RequestBody 
            @ApiParam(name = "product", required = true, value = "product to update")        
            ProductDTO productDTO) throws RestException {        
        ProductCRUDCommand command = createProductCRUDCommand(productCode, productDTO, Action.UPDATE);        
        ProductCommandResult result = commandExecutor.execute(command);        
        return createProductDTO(result.getResult());
    }

    @Override
    @ApiOperation(value = "Delete product", notes = "Delete product by product code")
    @ApiResponses({@ApiResponse(code = 422, message = "Unprocessable Entity")})
    @RequestMapping(value = DELETE_PRODUCT_URL, method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO deleteProduct(@PathVariable 
            @ApiParam(name = "productCode", required = true, value = "product code to delete")
            Long productCode) throws RestException {
        ProductCRUDCommand command = createProductCRUDCommand(productCode, Action.DELETE);        
        ProductCommandResult result = commandExecutor.execute(command);        
        return createProductDTO(result.getResult());
    }
    

    private ProductCRUDCommand createProductCRUDCommand(Long productCode, ProductDTO productDTO, Action action) {                 
        ProductCRUDCommand command = new ProductCRUDCommand();
        command.setAction(action);
        command.setCode(productCode);
        command.setDescription(productDTO.getDescription());
        command.setTitle(productDTO.getTitle());        
        return command;
    }
    
    private ProductCRUDCommand createProductCRUDCommand(Long productCode, Action action) { 
        ProductDTO productDTO = new ProductDTO();        
        return createProductCRUDCommand(productCode, productDTO, action);
    }
       
    private ProductCRUDCommand createProductCRUDCommand(ProductDTO productDTO, Action action) {
        return createProductCRUDCommand(productDTO.getCode(), productDTO, action);
    }

    private ProductDTO createProductDTO(Product result) {        
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCode(result.getCode());
        productDTO.setTitle(result.getTitle());
        productDTO.setDescription(result.getDescription());        
        return productDTO;
    }
        
}
