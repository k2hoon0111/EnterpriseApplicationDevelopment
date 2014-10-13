package lv.javaguru.ee.warehouse.integrations.controllers;

import lv.javaguru.ee.warehouse.integrations.RestException;
import lv.javaguru.ee.warehouse.integrations.domain.ProductDTO;
import lv.javaguru.ee.warehouse.integrations.jetty.EmbeddedJettyTest;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.HttpStatus;

/**
 *
 * @author dell
 */
public class ProductControllerTest extends EmbeddedJettyTest {
      
    @Test
    public void getProductTest() {        
        Long productCode = 1221L;
        ProductDTO productDTO = createProductWithCode(productCode);
        
        ProductDTO product = RestFixture.getProduct(productCode);
        assertNotNull(product);
        assertThat(product.getCode(), is(productCode));
    }
    
    @Test
    public void createProductTest() {
        Long productCode = 2332L;
        ProductDTO productDTO = createProductWithCode(productCode);
        assertThat(productDTO.getCode(), is(productCode));
    }
    
    @Test
    public void updateProductTest() {
        Long productCode = 3443L;
        ProductDTO productDTO = createProductWithCode(productCode);
        
        String newTitle = "updated title";
        productDTO.setTitle(newTitle);
        ProductDTO product = RestFixture.updateProduct(productCode, productDTO);
        assertNotNull(product);
        assertThat(product.getTitle(), is(newTitle));
    }
    
    @Test
    public void deleteProductTest() {
        Long productCode = 4554L;        
        ProductDTO productDTO = createProductWithCode(productCode);
        
        ProductDTO product = RestFixture.deleteProduct(productCode);
        assertNotNull(product);        
    }
    
    @Test
    public void getProductWithWrongIdTest() {        
        try {
            RestFixture.getProduct(Long.MAX_VALUE);
        } catch (RestException e) {
            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getHttpStatus());
        }
    }
    
    
    private ProductDTO createProductWithCode(Long productCode) {        
        ProductDTO productDTO = RestFixture.createProduct(getProductWithCode(productCode));
        assertNotNull(productDTO);
        return productDTO;
    }
    
    private ProductDTO getProductWithCode(Long productCode) {    
        ProductDTO productDTO = RestFixture.getDefaultProduct();
        productDTO.setCode(productCode);        
        return productDTO;
    } 
    
}
