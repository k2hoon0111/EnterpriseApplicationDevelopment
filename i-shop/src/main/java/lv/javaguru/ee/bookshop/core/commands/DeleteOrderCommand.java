package lv.javaguru.ee.bookshop.core.commands;

/**
 * Created by MumboJumbo on 21/09/14.
 */
public class DeleteOrderCommand implements DomainCommand<DeleteOrderResult> {
    private Long orderId;

    public DeleteOrderCommand(
            Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}