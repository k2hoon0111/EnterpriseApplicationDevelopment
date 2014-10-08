package lv.javaguru.ee.deliveryagency.core.commands.delivery;

import lv.javaguru.ee.deliveryagency.core.commands.DomainCommand;

/**
 * Created by Viktor on 15/09/2014.
 */
public class GetDeliveryCommand implements DomainCommand<GetDeliveryResult> {

    private Long deliveryId;

    public GetDeliveryCommand(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }
}
