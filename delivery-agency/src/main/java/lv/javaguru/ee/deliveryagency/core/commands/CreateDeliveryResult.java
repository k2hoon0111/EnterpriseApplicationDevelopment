package lv.javaguru.ee.deliveryagency.core.commands;

import lv.javaguru.ee.deliveryagency.core.domain.Delivery;

/**
 * Created by Viktor on 08/09/2014.
 */
public class CreateDeliveryResult implements DomainCommandResult {

    private Delivery delivery;

    public CreateDeliveryResult(Delivery delivery) {
        this.delivery = delivery;
    }

    public Delivery getDelivery() {
        return delivery;
    }

}
