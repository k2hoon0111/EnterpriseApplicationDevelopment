package lv.javaguru.ee.bookshop.core.commands;

/**
 * Created by MumboJumbo on 21/09/14.
 */
public class DeleteOrderDetailCommand implements DomainCommand<DeleteOrderDetailResult> {
    private Long orderDetailId;

    public DeleteOrderDetailCommand(
            Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
}