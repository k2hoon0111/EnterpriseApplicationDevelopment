package lv.javaguru.ee.warehouse.integrations.resourses;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.ProductPropertiesDTO;

/**
 *
 * @author dell
 */
public class ProductPropertiesResourceImpl implements ProductPropertiesResource {
    
    private final String baseWebServiceUrl;
    private final Client client;
    
    public ProductPropertiesResourceImpl(String baseWebServiceUrl) {
        this.baseWebServiceUrl = baseWebServiceUrl;
        this.client = ClientBuilder.newClient();
    }
    
    @Override
    public ProductPropertiesDTO getProductProperties(Long productCode, String prodPropName) throws RestException {
        try {
            String url = baseWebServiceUrl + GET_PROD_PROP_URL
                    .replace("{productCode}", String.valueOf(productCode))
                    .replace("{prodPropName}", prodPropName);
            return this.client.target(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(ProductPropertiesDTO.class);            
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    @Override
    public ProductPropertiesDTO createProductProperties(Long productCode, ProductPropertiesDTO prodPropDTO) throws RestException {        
        try {
            String url = baseWebServiceUrl + CREATE_PROD_PROP_URL
                    .replace("{productCode}", String.valueOf(productCode));
            return this.client.target(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(prodPropDTO), ProductPropertiesDTO.class);
        } catch (Throwable ex) {
            throw new RestException(ex);
        }  
    }

    @Override
    public ProductPropertiesDTO updateProductProperties(Long productCode, String prodPropName, ProductPropertiesDTO prodPropDTO) throws RestException {
        try {
            String url = baseWebServiceUrl + UPDATE_PROD_PROP_URL
                    .replace("{productCode}", String.valueOf(productCode))
                    .replace("{prodPropName}", prodPropName);
            return this.client.target(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.json(prodPropDTO), ProductPropertiesDTO.class);
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    @Override
    public ProductPropertiesDTO deleteProductProperties(Long productCode, String prodPropName) throws RestException {
        try {
            String url = baseWebServiceUrl + DELETE_PROD_PROP_URL
                    .replace("{productCode}", String.valueOf(productCode))
                    .replace("{prodPropName}", prodPropName);
            return this.client.target(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Content-Type", "application/json;charset=utf-8")
                    .delete(ProductPropertiesDTO.class);
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    public String getBaseWebServiceUrl() {
        return baseWebServiceUrl;
    }
           
}
