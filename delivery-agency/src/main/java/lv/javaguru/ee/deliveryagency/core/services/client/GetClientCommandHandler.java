package lv.javaguru.ee.deliveryagency.core.services.client;

import lv.javaguru.ee.deliveryagency.core.commands.client.GetClientCommand;
import lv.javaguru.ee.deliveryagency.core.commands.client.GetClientResult;
import lv.javaguru.ee.deliveryagency.core.database.DeliveryDAO;
import lv.javaguru.ee.deliveryagency.core.domain.Client;
import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
import lv.javaguru.ee.deliveryagency.core.services.DomainCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Viktor on 16/09/2014.
 */
@Component
public class GetClientCommandHandler
        implements DomainCommandHandler<GetClientCommand, GetClientResult> {

    @Autowired
    private DeliveryDAO deliveryDAO;


    @Override
    public GetClientResult execute(GetClientCommand command) {
        validateCommand(command);
        Delivery delivery = deliveryDAO.getRequired(command.getDeliveryId());
        Client client = delivery.getClient();

        if(client == null || !client.getClientId().equals(command.getClientId())) {
            throw new IllegalArgumentException("Client id not valid");
        }
        return new GetClientResult(client);
    }

    private void validateCommand(GetClientCommand command) {
        checkNotNull(command, "GetClientCommand must not be null");
        checkNotNull(command.getDeliveryId(), "DeliveryId must not be null");
        checkNotNull(command.getClientId(), "ClientId must not be null");
    }

    @Override
    public Class getCommandType() {
        return GetClientCommand.class;
    }
}
