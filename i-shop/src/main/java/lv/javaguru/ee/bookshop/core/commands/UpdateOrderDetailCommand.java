package lv.javaguru.ee.bookshop.core.commands;

/**
 * Created by MumboJumbo on 21/09/14.
 */

public class UpdateOrderDetailCommand implements DomainCommand<UpdateOrderDetailResult> {

    private Long orderDetailId;
    private Long bookId;
    private Long orderId;
    private int quantity;

    public UpdateOrderDetailCommand(Long orderDetailId, Long bookId, Long orderId, int quantity) {
        this.orderDetailId = orderDetailId;
        this.bookId = bookId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

