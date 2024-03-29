package lv.javaguru.ee.warehouse.integrations.controllers;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import lv.javaguru.ee.warehouse.core.CommandExecutor;
import lv.javaguru.ee.warehouse.core.command.Action;
import lv.javaguru.ee.warehouse.core.command.WarehouseCRUDCommand;
import lv.javaguru.ee.warehouse.core.command.WarehouseCommandResult;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;
import lv.javaguru.ee.warehouse.core.domain.WarehouseAddress;
import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.WarehouseDTO;
import lv.javaguru.ee.warehouse.integrations.resourses.WarehouseResource;
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
@Api(value = "Warehouse", description = "Warehouse CRUD API")
@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class WarehouseController implements WarehouseResource {
    
    @Autowired
    private CommandExecutor commandExecutor;
    
    @Override    
    @ApiOperation(value = "Get warehouse", notes = "Get warehouse by warehouseCode")
    @ApiResponses({@ApiResponse(code = 422, message = "Unprocessable Entity")})
    @RequestMapping(value = GET_WAREHOUSE_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)    
    public WarehouseDTO getWarehouse(@PathVariable 
            @ApiParam(name = "warehouseCode", required = true, value = "warehouse code") 
            String warehouseCode) throws RestException {
        WarehouseCRUDCommand command = createWarehouseCRUDCommand(warehouseCode, Action.GET);        
        WarehouseCommandResult result = commandExecutor.execute(command);        
        return createWarehouseDTO(result.getResult());
    }

    @Override
    @ApiOperation(value = "Create warehouse", notes = "Create new warehouse")
    @ApiResponses({@ApiResponse(code = 422, message = "Unprocessable Entity")})
    @RequestMapping(value = CREATE_WAREHOUSE_URL, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public WarehouseDTO createWarehouse(@RequestBody 
            @ApiParam(name = "warehouse", required = true, value = "warehouse to add")
            WarehouseDTO warehouseDTO) throws RestException {
        WarehouseCRUDCommand command = createWarehouseCRUDCommand(warehouseDTO, Action.CREATE);        
        WarehouseCommandResult result = commandExecutor.execute(command);        
        return createWarehouseDTO(result.getResult());
    }

    @Override
    @ApiOperation(value = "Update warehouse", notes = "Update warehouse")
    @ApiResponses({@ApiResponse(code = 422, message = "Unprocessable Entity")})
    @RequestMapping(value = UPDATE_WAREHOUSE_URL, method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public WarehouseDTO updateWarehouse(@PathVariable
            @ApiParam(name = "warehouseCode", required = true, value = "warehouse code to update") 
            String warehouseCode, 
            @ApiParam(name = "warehouse", required = true, value = "warehouse fields to update")
            @RequestBody 
            WarehouseDTO warehouseDTO) throws RestException {
        WarehouseCRUDCommand command = createWarehouseCRUDCommand(warehouseCode, warehouseDTO, Action.UPDATE);        
        WarehouseCommandResult result = commandExecutor.execute(command);        
        return createWarehouseDTO(result.getResult());
    }

    @Override
    @ApiOperation(value = "Delete warehouse", notes = "Delete warehouse by warehouse code")
    @ApiResponses({@ApiResponse(code = 422, message = "Unprocessable Entity")})
    @RequestMapping(value = DELETE_WAREHOUSE_URL, method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public WarehouseDTO deleteWarehouse(@PathVariable 
            @ApiParam(name = "warehouseCode", required = true, value = "warehouse code to delete") 
            String warehouseCode) throws RestException {
        WarehouseCRUDCommand command = createWarehouseCRUDCommand(warehouseCode, Action.DELETE);        
        WarehouseCommandResult result = commandExecutor.execute(command);        
        return createWarehouseDTO(result.getResult());
    }

    private WarehouseCRUDCommand createWarehouseCRUDCommand(String warehouseCode, WarehouseDTO warehouseDTO, Action action) {
        WarehouseCRUDCommand command = new WarehouseCRUDCommand();
        command.setAction(action);
        command.setTitle(warehouseCode);      
        
        WarehouseAddress address = new WarehouseAddress();
        address.setCity(warehouseDTO.getCity());
        address.setCountry(warehouseDTO.getCountry());
        address.setHouse(warehouseDTO.getHouse());
        address.setPostIndex(warehouseDTO.getPostIndex());
        address.setStreet(warehouseDTO.getStreet());        
        command.setAddress(address);
        command.setDescription(warehouseDTO.getDescription());
        return command;
    }

    private WarehouseCRUDCommand createWarehouseCRUDCommand(String warehouseCode, Action action) {        
        WarehouseDTO warehouseDTO = new WarehouseDTO();        
        return createWarehouseCRUDCommand(warehouseCode, warehouseDTO, action);
    }
    
    private WarehouseCRUDCommand createWarehouseCRUDCommand(WarehouseDTO warehouseDTO, Action action) {        
        return createWarehouseCRUDCommand(warehouseDTO.getTitle(), warehouseDTO, action);
    }
    
    private WarehouseDTO createWarehouseDTO(Warehouse result) {        
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        warehouseDTO.setTitle(result.getTitle());
        warehouseDTO.setCity(result.getAddress().getCity());
        warehouseDTO.setCountry(result.getAddress().getCountry());
        warehouseDTO.setDescription(result.getDescription());
        warehouseDTO.setHouse(result.getAddress().getHouse());
        warehouseDTO.setPostIndex(result.getAddress().getPostIndex());
        warehouseDTO.setStreet(result.getAddress().getStreet());
        return warehouseDTO;
    }
    
}
