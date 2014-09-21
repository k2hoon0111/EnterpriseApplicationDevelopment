package lv.javaguru.ee.bookshop.core.commands;

/**
 * Created by MumboJumbo on 21/09/14.
 */
public class GetOrderDetailCommand implements DomainCommand<GetOrderDetailResult> {

    private Long orderDetailId;

    public GetOrderDetailCommand(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

}