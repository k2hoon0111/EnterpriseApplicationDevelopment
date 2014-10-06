package lv.javaguru.ee.warehouse.integrations.resourses;

import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.ProductPropertiesDTO;

/**
 *
 * @author dell
 */
public interface ProductPropertiesResource {
    
    static final String GET_PROD_PROP_URL = "/rest/product/{productCode}/productproperties/{prodPropName}";
    static final String CREATE_PROD_PROP_URL = "/rest/product/{productCode}/productproperties";
    static final String UPDATE_PROD_PROP_URL = "/rest/product/{productCode}/productproperties/{prodPropName}";
    static final String DELETE_PROD_PROP_URL = "/rest/product/{productCode}/productproperties/{prodPropName}";
    
    ProductPropertiesDTO getProductProperties(Long productCode, String prodPropName) throws RestException;
    
    ProductPropertiesDTO createProductProperties(Long productCode, ProductPropertiesDTO prodPropDTO) throws RestException;
    
    ProductPropertiesDTO updateProductProperties(Long productCode, String prodPropName, ProductPropertiesDTO prodPropDTO) throws RestException;
    
    ProductPropertiesDTO deleteProductProperties(Long productCode, String prodPropName) throws RestException;
    
}
