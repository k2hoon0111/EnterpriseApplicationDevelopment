package lv.javaguru.ee.warehouse.integrations.domain;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author dell
 */
public class OrderDTO {
 
    private final Set<Long> productCodes = new HashSet<>();

    private String warehouseCode;

    private Integer quantity;

    private Integer amount;

    public OrderDTO() {
    }

    public OrderDTO(Set<Long> productCodes, String warehouseCode, Integer quantity, Integer amount) {
        this.productCodes.addAll(productCodes);
        this.warehouseCode = warehouseCode;
        this.quantity = quantity;
        this.amount = amount;
    }

    public Set<Long> getProductCodess() {
        return this.productCodes;
    }
    
    public void setProductCodes(Set<Long> productCode) {
        this.productCodes.clear();
        this.productCodes.addAll(productCode);
    }
    
    public void addProductCode(Long productCode) {
        this.productCodes.add(productCode);    
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
