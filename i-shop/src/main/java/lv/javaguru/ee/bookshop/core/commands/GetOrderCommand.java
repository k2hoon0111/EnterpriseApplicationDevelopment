package lv.javaguru.ee.bookshop.core.commands;

/**
 * Created by MumboJumbo on 20/09/14.
 */

public class GetOrderCommand implements DomainCommand<GetOrderResult> {

    private Long orderId;

    public GetOrderCommand(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

}
