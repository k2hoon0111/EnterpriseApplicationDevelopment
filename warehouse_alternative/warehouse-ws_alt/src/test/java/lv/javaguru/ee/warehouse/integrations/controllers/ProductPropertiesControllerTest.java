package lv.javaguru.ee.warehouse.integrations.controllers;

import lv.javaguru.ee.warehouse.integrations.domain.ProductDTO;
import lv.javaguru.ee.warehouse.integrations.domain.ProductPropertiesDTO;
import lv.javaguru.ee.warehouse.integrations.jetty.EmbeddedJettyTest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 *
 * @author dell
 */
//@Ignore
public class ProductPropertiesControllerTest extends EmbeddedJettyTest {
    
    private static ProductDTO productDTO;
    
    @BeforeClass    
    public static void initTest() {    
        System.out.println("ProductPropertiesControllerTest init called!!!");
        ProductDTO prod = RestFixture.getDefaultProduct();
        prod.setCode(7799L);
        productDTO = RestFixture.createProduct(prod);
    }

    @AfterClass
    public static void cleanTest() {
        System.out.println("ProductPropertiesControllerTest clean called!!!");
        productDTO = null;        
    }
    
    @Test
    public void getProductPropertiesTest() {    
        String propertyName = "property_1";
        ProductPropertiesDTO prodPropsDTO = createProdPropsWithName(propertyName);
        
        ProductPropertiesDTO prodProps = RestFixture.getProdProps(productDTO.getCode(), prodPropsDTO.getName());
        assertNotNull(prodProps);
        assertThat(prodProps.getName(), is(prodPropsDTO.getName()));
        assertThat(prodProps.getValue(), is(prodPropsDTO.getValue()));
    }
    
    @Test
    public void createProductPropertiesTest() {
        String propertyName = "property_2";
        ProductPropertiesDTO prodPropsDTO = createProdPropsWithName(propertyName);
                
        assertThat(propertyName, is(prodPropsDTO.getName()));
        assertThat(prodPropsDTO.getValue(), is(notNullValue()));
    }
    
    @Test
    public void updateProductPropertiesTest() {    
        String propertyName = "property_3";
        ProductPropertiesDTO prodPropsDTO = createProdPropsWithName(propertyName);
        
        String propertyValue = "updated value";        
        ProductPropertiesDTO prodProps = RestFixture.updateProdProps(productDTO.getCode(), propertyName, propertyValue);
        assertNotNull(prodProps);
        assertThat(propertyName, is(prodPropsDTO.getName()));
        assertThat(propertyValue, is(prodProps.getValue()));
    }
    
    @Test
    public void deleteProductPropertiesTest() {
        String propertyName = "property_4";
        ProductPropertiesDTO prodPropsDTO = createProdPropsWithName(propertyName);
        
        ProductPropertiesDTO prodProps = RestFixture.deleteProdProps(productDTO.getCode(), propertyName);
        assertNotNull(prodProps);
        assertThat(propertyName, is(prodPropsDTO.getName()));
        assertThat(prodPropsDTO.getValue(), is(notNullValue()));
    }
    
    private ProductPropertiesDTO getProdPropsWithName(String name) {    
        ProductPropertiesDTO prodProps = RestFixture.getDefaultProdProps();
        prodProps.setName(name);
        return prodProps;
    }
    
    private ProductPropertiesDTO createProdPropsWithName(String name) {        
        ProductPropertiesDTO prodProps = RestFixture.createProdProps(
                productDTO.getCode(), 
                getProdPropsWithName(name)
        );        
        assertNotNull(prodProps);        
        return prodProps;
    }
    
}
