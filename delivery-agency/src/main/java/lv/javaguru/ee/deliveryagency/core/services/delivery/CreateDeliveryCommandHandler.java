package lv.javaguru.ee.deliveryagency.core.services.delivery;

import lv.javaguru.ee.deliveryagency.core.commands.delivery.CreateDeliveryResult;
import lv.javaguru.ee.deliveryagency.core.services.DomainCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.javaguru.ee.deliveryagency.core.commands.delivery.CreateDeliveryCommand;
import lv.javaguru.ee.deliveryagency.core.database.DeliveryDAO;
import lv.javaguru.ee.deliveryagency.core.domain.Delivery;

/**
 * Created by Viktor on 08/09/2014.
 */
@Component
public class CreateDeliveryCommandHandler
        implements DomainCommandHandler<CreateDeliveryCommand, CreateDeliveryResult> {

    @Autowired
    private DeliveryDAO deliveryDAO;


    @Override
    public CreateDeliveryResult execute(CreateDeliveryCommand command) {
        Delivery delivery = new Delivery();
        deliveryDAO.create(delivery);
        return new CreateDeliveryResult(delivery);
    }

    @Override
    public Class getCommandType() {
        return CreateDeliveryCommand.class;
    }

}
