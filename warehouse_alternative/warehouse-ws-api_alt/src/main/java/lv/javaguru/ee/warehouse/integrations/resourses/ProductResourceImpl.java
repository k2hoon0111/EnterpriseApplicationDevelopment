package lv.javaguru.ee.warehouse.integrations.resourses;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.ProductDTO;

/**
 *
 * @author dell
 */
public class ProductResourceImpl implements ProductResource {
    
    private final String baseWebServiceUrl;
    private final Client client;
    
    
    public ProductResourceImpl(String baseWebServiceUrl) {
        this.baseWebServiceUrl = baseWebServiceUrl;
        this.client = ClientBuilder.newClient();
    }
    
    @Override
    public ProductDTO getProduct(Long productCode) throws RestException {
       try {
            String url = baseWebServiceUrl + 
                    GET_PRODUCT_URL.replace("{productCode}", String.valueOf(productCode));
            return this.client.target(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(ProductDTO.class);             
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) throws RestException {        
        try {
            String url = baseWebServiceUrl + CREATE_PRODUCT_URL;
            return this.client.target(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.json(productDTO), ProductDTO.class);            
        } catch (Throwable ex) {
            throw new RestException(ex);
        }        
    }

    @Override
    public ProductDTO updateProduct(Long productCode, ProductDTO productDTO) throws RestException {
        try {
            String url = baseWebServiceUrl + 
                    UPDATE_PRODUCT_URL.replace("{productCode}", String.valueOf(productCode));
            return this.client.target(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.json(productDTO), ProductDTO.class);            
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    @Override
    public ProductDTO deleteProduct(Long productCode) throws RestException {
        try {
            String url = baseWebServiceUrl + 
                    DELETE_PRODUCT_URL.replace("{productCode}", String.valueOf(productCode));
            return this.client.target(url)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header("Content-Type", "application/json;charset=utf-8")
                    .delete(ProductDTO.class);            
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    public String getBaseWebServiceUrl() {
        return baseWebServiceUrl;
    }
 
}
