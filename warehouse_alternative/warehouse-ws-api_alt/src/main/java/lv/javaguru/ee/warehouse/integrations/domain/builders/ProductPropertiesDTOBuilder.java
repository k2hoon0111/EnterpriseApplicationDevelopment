package lv.javaguru.ee.warehouse.integrations.domain.builders;

import lv.javaguru.ee.warehouse.integrations.domain.ProductPropertiesDTO;

public class ProductPropertiesDTOBuilder {

    private Long productCode;
    private String name;
    private String value;

    private ProductPropertiesDTOBuilder() {
    }

    public static ProductPropertiesDTOBuilder createProductPropertiesDTO() {
        return new ProductPropertiesDTOBuilder();
    }

    public ProductPropertiesDTOBuilder setProductCode(Long productCode) {
        this.productCode = productCode;
        return this;
    }

    public ProductPropertiesDTOBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductPropertiesDTOBuilder setValue(String value) {
        this.value = value;
        return this;
    }

    public ProductPropertiesDTO build() {
        ProductPropertiesDTO pp = new ProductPropertiesDTO();
        pp.setProductCode(productCode);
        pp.setName(name);        
        pp.setValue(value);        
        return pp;
    }

}
