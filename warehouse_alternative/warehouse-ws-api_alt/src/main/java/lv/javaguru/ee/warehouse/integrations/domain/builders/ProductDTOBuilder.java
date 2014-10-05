package lv.javaguru.ee.warehouse.integrations.domain.builders;

import lv.javaguru.ee.warehouse.integrations.domain.ProductDTO;

public class ProductDTOBuilder {

    private Long code;
    private String title;
    private String description;

    private ProductDTOBuilder() {
    }

    public static ProductDTOBuilder createProductDTO() {
        return new ProductDTOBuilder();
    }

    public ProductDTOBuilder setCode(Long code) {
        this.code = code;
        return this;
    }

    public ProductDTOBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ProductDTOBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductDTO build() {
        ProductDTO product = new ProductDTO();
        product.setCode(code);
        product.setTitle(title);
        product.setDescription(description);
        return product;
    }

}
