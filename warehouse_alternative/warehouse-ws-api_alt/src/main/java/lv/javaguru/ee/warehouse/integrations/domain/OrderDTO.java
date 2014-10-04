package lv.javaguru.ee.warehouse.integrations.domain;

/**
 *
 * @author dell
 */
public class OrderDTO {
 
    private Long productCode;

    private String warehouseCode;

    private Integer quantity;

    private Integer amount;

    public OrderDTO() {
    }

    public OrderDTO(Long productCode, String warehouseCode, Integer quantity, Integer amount) {
        this.productCode = productCode;
        this.warehouseCode = warehouseCode;
        this.quantity = quantity;
        this.amount = amount;
    }

    
    
    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }
    
    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    
    
}
