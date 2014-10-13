package lv.javaguru.ee.warehouse.integrations.resourses;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.WarehouseDTO;

/**
 *
 * @author dell
 */
public class WarehouseResourceImpl implements WarehouseResource {
    
    private final String baseWebServiceUrl;
    private final Client client;
    
    public WarehouseResourceImpl(String baseWebServiceUrl) {
        this.baseWebServiceUrl = baseWebServiceUrl;
        this.client = ClientBuilder.newClient();
    }
            
    @Override
    public WarehouseDTO getWarehouse(String warehouseCode) throws RestException {
        try {
            String url = baseWebServiceUrl + GET_WAREHOUSE_URL.replace("{warehouseCode}", warehouseCode);            
            return this.client.target(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(WarehouseDTO.class);                          
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    @Override
    public WarehouseDTO createWarehouse(WarehouseDTO warehouseDTO) throws RestException {
        try {
            String url = baseWebServiceUrl + CREATE_WAREHOUSE_URL;
            return this.client.target(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(warehouseDTO), WarehouseDTO.class);
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    @Override
    public WarehouseDTO updateWarehouse(String warehouseCode, WarehouseDTO warehouseDTO) throws RestException {
        try {
            String url = baseWebServiceUrl + UPDATE_WAREHOUSE_URL.replace("{warehouseCode}", warehouseCode);
            return this.client.target(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.json(warehouseDTO), WarehouseDTO.class);                        
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    @Override
    public WarehouseDTO deleteWarehouse(String warehouseCode) throws RestException {
        try {
            String url = baseWebServiceUrl + 
                    DELETE_WAREHOUSE_URL.replace("{warehouseCode}", warehouseCode);
             return this.client.target(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Content-Type", "application/json;charset=utf-8")
                    .delete(WarehouseDTO.class);
        } catch (Throwable ex) {
            throw new RestException(ex);
        }

    }

    public String getBaseWebServiceUrl() {
        return baseWebServiceUrl;
    }

}
