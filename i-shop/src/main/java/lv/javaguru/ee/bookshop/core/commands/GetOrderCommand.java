package lv.javaguru.ee.bookshop.core.commands;

/**
 * Created by MumboJumbo on 20/09/14.
 */

public class GetOrderCommand implements DomainCommand<GetOrderResult> {

    private Long orderId;
    private Long accountId;

    public GetOrderCommand(Long accountId, Long orderId) {
        this.orderId = orderId;
        this.accountId = accountId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getAccountId() {
        return accountId;
    }
}
