package lv.javaguru.ee.deliveryagency.core.services;

import lv.javaguru.ee.deliveryagency.core.DomainCommandService;
import lv.javaguru.ee.deliveryagency.core.commands.CreateDeliveryCommand;
import lv.javaguru.ee.deliveryagency.core.commands.CreateDeliveryCommandResult;
import lv.javaguru.ee.deliveryagency.core.database.DeliveryDAO;
import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Viktor on 08/09/2014.
 */
@Component
public class CreateDeliveryService
        implements DomainCommandService<CreateDeliveryCommand, CreateDeliveryCommandResult> {

    @Autowired
    private DeliveryDAO deliveryDAO;


    @Override
    public CreateDeliveryCommandResult execute(CreateDeliveryCommand command) {
        Delivery delivery = new Delivery();
        deliveryDAO.create(delivery);
        return new CreateDeliveryCommandResult(delivery);
    }

    @Override
    public Class getCommandType() {
        return CreateDeliveryCommand.class;
    }

}
