package lv.javaguru.ee.warehouse.integrations.resourses;

import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.ProductPropertiesDTO;
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
public class ProductPropertiesResourceImpl implements ProductPropertiesResource {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private final String baseWebServiceUrl;

    public ProductPropertiesResourceImpl(String baseWebServiceUrl) {
        this.baseWebServiceUrl = baseWebServiceUrl;
    }
    
    @Override
    public ProductPropertiesDTO getProductProperties(Long productCode, String prodPropName) throws RestException {
        try {
            String url = baseWebServiceUrl + GET_PROD_PROP_URL
                    .replace("{productCode}", String.valueOf(productCode))
                    .replace("{prodPropName}", prodPropName);
            
            return REST_TEMPLATE.getForObject(url, ProductPropertiesDTO.class);
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    @Override
    public ProductPropertiesDTO createProductProperties(Long productCode, ProductPropertiesDTO prodPropDTO) throws RestException {        
        try {
            String url = baseWebServiceUrl + CREATE_PROD_PROP_URL
                    .replace("{productCode}", String.valueOf(productCode));
            return REST_TEMPLATE.postForObject(url, prodPropDTO, ProductPropertiesDTO.class);
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

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity httpEntity = new HttpEntity(prodPropDTO, headers);
            
            ResponseEntity<ProductPropertiesDTO> responseEntity
                    = REST_TEMPLATE.exchange(url, HttpMethod.PUT, httpEntity, ProductPropertiesDTO.class);

            return responseEntity.getBody();
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

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity httpEntity = new HttpEntity(headers);
            
            ResponseEntity<ProductPropertiesDTO> responseEntity
                    = REST_TEMPLATE.exchange(url, HttpMethod.DELETE, httpEntity, ProductPropertiesDTO.class);

            return responseEntity.getBody();
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    public String getBaseWebServiceUrl() {
        return baseWebServiceUrl;
    }
           
}
