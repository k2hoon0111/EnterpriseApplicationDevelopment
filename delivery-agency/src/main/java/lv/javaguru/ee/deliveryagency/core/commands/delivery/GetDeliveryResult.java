package lv.javaguru.ee.deliveryagency.core.commands.delivery;

import lv.javaguru.ee.deliveryagency.core.commands.DomainCommandResult;
import lv.javaguru.ee.deliveryagency.core.domain.Delivery;

/**
 * Created by Viktor on 15/09/2014.
 */
public class GetDeliveryResult implements DomainCommandResult {

    private Delivery delivery;

    public GetDeliveryResult(Delivery delivery) {
        this.delivery = delivery;
    }

    public Delivery getDelivery() {
        return delivery;
    }
}
