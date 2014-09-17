package lv.javaguru.ee.deliveryagency.core.services;

import lv.javaguru.ee.deliveryagency.core.commands.GetDeliveryCommand;
import lv.javaguru.ee.deliveryagency.core.commands.GetDeliveryResult;
import lv.javaguru.ee.deliveryagency.core.database.DeliveryDAO;
import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Viktor on 15/09/2014.
 */
@Component
public class GetDeliveryCommandHandler
        implements DomainCommandHandler<GetDeliveryCommand, GetDeliveryResult> {

    @Autowired
    private DeliveryDAO deliveryDAO;


    @Override
    public GetDeliveryResult execute(GetDeliveryCommand command) {
        validateCommand(command);
        Delivery delivery = deliveryDAO.getById(command.getDeliveryId());
        return new GetDeliveryResult(delivery);
    }

    private void validateCommand(GetDeliveryCommand command) {
        checkNotNull(command, "GetDeliveryCommand must not be null");
        checkNotNull(command.getDeliveryId(), "DeliveryId must not be null");
    }

    @Override
    public Class getCommandType() {
        return GetDeliveryCommand.class;
    }
}
