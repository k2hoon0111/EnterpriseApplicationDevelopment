package lv.javaguru.ee.warehouse.integrations.resourses;

import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.ProductDTO;

/**
 *
 * @author dell
 */
public interface ProductResource {
    
    static final String GET_PRODUCT_URL = "/rest/product/{productCode}";
    static final String CREATE_PRODUCT_URL = "/rest/product";
    static final String UPDATE_PRODUCT_URL = "/rest/product/{productCode}";
    static final String DELETE_PRODUCT_URL = "/rest/product/{productCode}";
        
    ProductDTO getProduct(Long productCode) throws RestException;
    
    ProductDTO createProduct(ProductDTO productDTO) throws RestException;
    
    ProductDTO updateProduct(Long productCode, ProductDTO productDTO) throws RestException;
    
    ProductDTO deleteProduct(Long productCode) throws RestException;
    
}
