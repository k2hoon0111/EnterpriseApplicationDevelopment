package lv.javaguru.ee.warehouse.integrations.domain;

/**
 *
 * @author dell
 */
public class ProductDTO {
    
    private Long code;
        
    private String title;
        
    private String description;

    public ProductDTO() {
    }
        
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
            
}
