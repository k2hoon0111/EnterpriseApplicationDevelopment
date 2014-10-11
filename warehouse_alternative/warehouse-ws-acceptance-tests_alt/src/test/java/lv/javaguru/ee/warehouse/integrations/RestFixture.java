package lv.javaguru.ee.warehouse.integrations;

import java.util.Random;
import lv.javaguru.ee.warehouse.integrations.domain.OrderDTO;
import lv.javaguru.ee.warehouse.integrations.domain.ProductDTO;
import lv.javaguru.ee.warehouse.integrations.domain.ProductPropertiesDTO;
import lv.javaguru.ee.warehouse.integrations.domain.WarehouseDTO;
import lv.javaguru.ee.warehouse.integrations.resourses.OrderResource;
import lv.javaguru.ee.warehouse.integrations.resourses.OrderResourceImpl;
import lv.javaguru.ee.warehouse.integrations.resourses.ProductPropertiesResource;
import lv.javaguru.ee.warehouse.integrations.resourses.ProductPropertiesResourceImpl;
import lv.javaguru.ee.warehouse.integrations.resourses.ProductResource;
import lv.javaguru.ee.warehouse.integrations.resourses.ProductResourceImpl;
import lv.javaguru.ee.warehouse.integrations.resourses.WarehouseResource;
import lv.javaguru.ee.warehouse.integrations.resourses.WarehouseResourceImpl;

/**
 * Created by Viktor on 16/09/2014.
 */
public class RestFixture {

    
    private static final OrderResource orderResource = createOrderResource();
    private static final ProductResource productResource = createProductResource();    
    private static final WarehouseResource warehouseResource = createWarehouseResource();
    private static final ProductPropertiesResource prodPropResource = createProdPropResource();
    
    public static final Random random = new Random();
    
    private static OrderResource createOrderResource() {
        PropertiesReader propertiesReader = new PropertiesReader();
        String baseUrl = propertiesReader.getBaseUrl();
        return new OrderResourceImpl(baseUrl);
    }
    
    private static ProductResource createProductResource() {
        PropertiesReader propertiesReader = new PropertiesReader();
        String baseUrl = propertiesReader.getBaseUrl();
        return new ProductResourceImpl(baseUrl);
    }

    private static ProductPropertiesResource createProdPropResource() {
        PropertiesReader propertiesReader = new PropertiesReader();
        String baseUrl = propertiesReader.getBaseUrl();
        return new ProductPropertiesResourceImpl(baseUrl);
    }

    private static WarehouseResource createWarehouseResource() {
        PropertiesReader propertiesReader = new PropertiesReader();
        String baseUrl = propertiesReader.getBaseUrl();
        return new WarehouseResourceImpl(baseUrl);
    }


    private static Long getRanfomLong() {
        Long number = random.nextLong();
        return Math.max(number, (-1) * number);        
    }
    
	/////////// Order methods /////////////
    
    public static OrderDTO getDefaultOrder(String warehouseCode, Long productCode) {    
        OrderDTO order = new OrderDTO();
        order.setWarehouseCode(warehouseCode);
        order.setProductCode(productCode);
        order.setAmount(10);
        order.setQuantity(1);
        return order;
    }
    
    public static OrderDTO createIncomingOrder(OrderDTO orderDTO) {
        return orderResource.createIncomingOrder(orderDTO);
    }

    public static OrderDTO createOutgoingOrder(OrderDTO orderDTO) {
        return orderResource.createOutgoingOrder(orderDTO);
    }

    
        ////////////// Product methods ////////////////
            
    public static ProductDTO getDefaultProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCode(getRanfomLong());
        productDTO.setTitle("Effective Java 11");
        productDTO.setDescription("Joshua Bloch 'Effective Java 11'");
        return productDTO;
    }
    
    public static ProductDTO createProduct(ProductDTO productDTO) {
	return productResource.createProduct(productDTO); 
    }

    public static ProductDTO getProduct(Long productCode) {
	return productResource.getProduct(productCode);
    }
    
    public static ProductDTO updateProduct(Long productCode, ProductDTO productDTO) {
	return productResource.updateProduct(productCode, productDTO);
    }

    public static ProductDTO deleteProduct(Long productCode) {
	return productResource.deleteProduct(productCode);
    }
        
        ////////////// Warehouse methods ////////////////
           
    public static WarehouseDTO getDefaultWarehouse() {    
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        warehouseDTO.setTitle(String.valueOf(getRanfomLong()));
        warehouseDTO.setCity("London");
        warehouseDTO.setCountry("GB");
        warehouseDTO.setDescription("Main warehouse");
        warehouseDTO.setHouse("17/6");
        warehouseDTO.setPostIndex("GB 23476");
        warehouseDTO.setStreet("Trafalgar square");
        return warehouseDTO;
    }
    
    public static WarehouseDTO createWarehouse(WarehouseDTO warehouseDTO) {
	return warehouseResource.createWarehouse(warehouseDTO); 
    }

    public static WarehouseDTO getWarehouse(String warehouseCode) {
	return warehouseResource.getWarehouse(warehouseCode);
    }
    
    public static WarehouseDTO updateWarehouse(String warehouseCode, WarehouseDTO warehouseDTO) {
	return warehouseResource.updateWarehouse(warehouseCode, warehouseDTO);
    }

    public static WarehouseDTO deleteWarehouse(String warehouseCode) {
	return warehouseResource.deleteWarehouse(warehouseCode);
    }
    
    
        ////////////// ProductProperties methods ////////////////
       
    public static ProductPropertiesDTO getDefaultProdProps() {    
        ProductPropertiesDTO prodProps = new ProductPropertiesDTO();
        prodProps.setName(String.valueOf(getRanfomLong()));
        prodProps.setValue("Joshua Blosh");        
        return prodProps;
    }
    
    public static ProductPropertiesDTO createProdProps(Long productCode, ProductPropertiesDTO prodPropsDTO) {
	return prodPropResource.createProductProperties(productCode, prodPropsDTO); 
    }

    public static ProductPropertiesDTO getProdProps(Long productCode, String prodPropName) {
	return prodPropResource.getProductProperties(productCode, prodPropName);
    }
    
    public static ProductPropertiesDTO updateProdProps(Long productCode, String prodPropName, String prodPropValue) {
        ProductPropertiesDTO prodProps = new ProductPropertiesDTO();
        prodProps.setName(prodPropName);
        prodProps.setValue(prodPropValue); 
	return prodPropResource.updateProductProperties(productCode, prodPropName, prodProps);
    }

    public static ProductPropertiesDTO deleteProdProps(Long productCode, String prodPropName) {
	return prodPropResource.deleteProductProperties(productCode, prodPropName);
    }

}
