package lv.javaguru.ee.warehouse.integrations.domain.builders;

import java.util.HashSet;
import java.util.Set;
import lv.javaguru.ee.warehouse.integrations.domain.OrderDTO;

public class OrderDTOBuilder {

    private final Set<Long> productCodes = new HashSet<>();
    private String warehouseCode;
    private Integer quantity;
    private Integer amount;

    private OrderDTOBuilder() {
    }

    public static OrderDTOBuilder createOrderDTO() {
        return new OrderDTOBuilder();
    }

    public OrderDTOBuilder setProductCode(Set<Long> productCodes) {
        this.productCodes.clear();
        this.productCodes.addAll(productCodes);
        return this;
    }

    public OrderDTOBuilder addProductCode(Long productCode) {
        this.productCodes.add(productCode);
        return this;
    }
    
    public OrderDTOBuilder setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
        return this;
    }

    public OrderDTOBuilder setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderDTOBuilder setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public OrderDTO build() {
        OrderDTO order = new OrderDTO();
        order.setWarehouseCode(warehouseCode);
        order.setProductCodes(productCodes);
        order.setAmount(amount);
        order.setQuantity(quantity);        
        return order;
    }

}
