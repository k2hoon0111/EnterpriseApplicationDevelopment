package lv.javaguru.ee.bookshop.core.commands;

/**
 * Created by MumboJumbo on 21/09/14.
 */
public class CreateOrderDetailCommand implements DomainCommand<CreateOrderDetailResult> {

    private Long bookId;
    private Long orderId;
    private int quantity;

    public CreateOrderDetailCommand(Long bookId, Long orderId, int quantity) {
        this.bookId = bookId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public int getQuantity() {
        return quantity;
    }
}
