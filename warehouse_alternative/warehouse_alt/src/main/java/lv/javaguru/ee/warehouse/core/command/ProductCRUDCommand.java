package lv.javaguru.ee.warehouse.core.command;

/**
 *
 * @author dell
 */
public class ProductCRUDCommand implements DomainCRUDCommand<ProductCommandResult> {
       
    private Action action;
                
    private Long code;
        
    private String title;
        
    private String description;
          
    public ProductCRUDCommand() {
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

    @Override
    public Action getAction() {
        return action;
    }

    @Override
    public void setAction(Action action) {
        this.action = action;
    }
         
}
