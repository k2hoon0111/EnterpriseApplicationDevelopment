package lv.javaguru.ee.warehouse.integrations.resourses;

import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.ProductDTO;
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
public class ProductResourceImpl implements ProductResource {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private final String baseWebServiceUrl;

    public ProductResourceImpl(String baseWebServiceUrl) {
        this.baseWebServiceUrl = baseWebServiceUrl;
    }
    
    @Override
    public ProductDTO getProduct(Long productCode) throws RestException {
       try {
            String url = baseWebServiceUrl + 
                    GET_PRODUCT_URL.replace("{productCode}", String.valueOf(productCode));
            
            return REST_TEMPLATE.getForObject(url, ProductDTO.class);
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) throws RestException {        
        try {
            String url = baseWebServiceUrl + CREATE_PRODUCT_URL;
            return REST_TEMPLATE.postForObject(url, productDTO, ProductDTO.class);
        } catch (Throwable ex) {
            throw new RestException(ex);
        }        
    }

    @Override
    public ProductDTO updateProduct(Long productCode, ProductDTO productDTO) throws RestException {
        try {
            String url = baseWebServiceUrl + 
                    UPDATE_PRODUCT_URL.replace("{productCode}", String.valueOf(productCode));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<ProductDTO> httpEntity = new HttpEntity<>(productDTO, headers);
            ResponseEntity<ProductDTO> responseEntity
                    = REST_TEMPLATE.exchange(url, HttpMethod.PUT, httpEntity, ProductDTO.class);

            return responseEntity.getBody();
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    @Override
    public ProductDTO deleteProduct(Long productCode) throws RestException {
        try {
            String url = baseWebServiceUrl + 
                    DELETE_PRODUCT_URL.replace("{productCode}", String.valueOf(productCode));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<ProductDTO> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<ProductDTO> responseEntity
                    = REST_TEMPLATE.exchange(url, HttpMethod.DELETE, httpEntity, ProductDTO.class);

            return responseEntity.getBody();
        } catch (Throwable ex) {
            throw new RestException(ex);
        }
    }

    public String getBaseWebServiceUrl() {
        return baseWebServiceUrl;
    }
 
}
