package lv.javaguru.ee.warehouse.integrations.resourses;

import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.WarehouseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author dell
 */
public class WarehouseResourceImpl implements WarehouseResource {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private final String baseWebServiceUrl;

    public WarehouseResourceImpl(String baseWebServiceUrl) {
        this.baseWebServiceUrl = baseWebServiceUrl;
    }
            
    @Override
    public WarehouseDTO getWarehouse(String warehouseCode) throws RestException {
        try {
            String url = baseWebServiceUrl + GET_WAREHOUSE_URL.replace("{warehouseCode}", warehouseCode);
            return REST_TEMPLATE.getForObject(url, WarehouseDTO.class);
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    @Override
    public WarehouseDTO createWarehouse(WarehouseDTO warehouseDTO) throws RestException {
        try {
            String url = baseWebServiceUrl + CREATE_WAREHOUSE_URL;
            return REST_TEMPLATE.postForObject(url, warehouseDTO, WarehouseDTO.class);
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    @Override
    public WarehouseDTO updateWarehouse(String warehouseCode, WarehouseDTO warehouseDTO) throws RestException {
        try {
            String url = baseWebServiceUrl + UPDATE_WAREHOUSE_URL.replace("{warehouseCode}", warehouseCode);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<WarehouseDTO> httpEntity = new HttpEntity<>(warehouseDTO, headers);
            ResponseEntity<WarehouseDTO> responseEntity
                    = REST_TEMPLATE.exchange(url, HttpMethod.PUT, httpEntity, WarehouseDTO.class);

            return responseEntity.getBody();
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    @Override
    public WarehouseDTO deleteWarehouse(String warehouseCode) throws RestException {
        try {
            String url = baseWebServiceUrl + 
                    DELETE_WAREHOUSE_URL.replace("{warehouseCode}", warehouseCode);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<WarehouseDTO> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<WarehouseDTO> responseEntity
                    = REST_TEMPLATE.exchange(url, HttpMethod.DELETE, httpEntity, WarehouseDTO.class);

            return responseEntity.getBody();
        } catch (Throwable ex) {
            throw new RestException(ex);
        }

    }

    public String getBaseWebServiceUrl() {
        return baseWebServiceUrl;
    }

}
