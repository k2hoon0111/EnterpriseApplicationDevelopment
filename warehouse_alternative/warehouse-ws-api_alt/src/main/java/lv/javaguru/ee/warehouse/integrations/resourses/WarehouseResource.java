package lv.javaguru.ee.warehouse.integrations.resourses;

import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.WarehouseDTO;

/**
 *
 * @author dell
 */
public interface WarehouseResource {
    
    static final String GET_WAREHOUSE_URL = "/rest/warehouse/{warehouseCode}";
    static final String CREATE_WAREHOUSE_URL = "/rest/warehouse";
    static final String UPDATE_WAREHOUSE_URL = "/rest/warehouse/{warehouseCode}";
    static final String DELETE_WAREHOUSE_URL = "/rest/warehouse/{warehouseCode}";
 
    WarehouseDTO getWarehouse(Long warehouseCode) throws RestException;
    
    WarehouseDTO createWarehouse(WarehouseDTO warehouseDTO) throws RestException;
    
    WarehouseDTO updateWarehouse(Long warehouseCode, WarehouseDTO warehouseDTO) throws RestException;
    
    WarehouseDTO deleteWarehouse(Long warehouseCode) throws RestException;
    
}
