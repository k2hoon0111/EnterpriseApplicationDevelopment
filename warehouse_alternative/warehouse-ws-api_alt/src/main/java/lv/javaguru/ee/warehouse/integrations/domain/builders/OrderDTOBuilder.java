package lv.javaguru.ee.warehouse.integrations.domain.builders;

import lv.javaguru.ee.warehouse.integrations.domain.OrderDTO;

public class OrderDTOBuilder {

    private Long productCode;
    private String warehouseCode;
    private Integer quantity;
    private Integer amount;

    private OrderDTOBuilder() {
    }

    public static OrderDTOBuilder createOrderDTO() {
        return new OrderDTOBuilder();
    }

    public OrderDTOBuilder setProductCode(Long productCode) {
        this.productCode = productCode;
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
        order.setProductCode(productCode);
        order.setAmount(amount);
        order.setQuantity(quantity);        
        return order;
    }

}
