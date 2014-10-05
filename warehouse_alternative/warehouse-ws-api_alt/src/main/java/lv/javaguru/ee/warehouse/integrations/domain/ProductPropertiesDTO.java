package lv.javaguru.ee.warehouse.integrations.domain;

/**
 *
 * @author dell
 */
public class ProductPropertiesDTO {
 
    private Long productCode;
    
    private String name;
    
    private String value;

    public ProductPropertiesDTO() {
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
            
}
