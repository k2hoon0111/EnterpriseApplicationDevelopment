package lv.javaguru.ee.deliveryagency.core.services.client;

import lv.javaguru.ee.deliveryagency.core.commands.client.UpdateClientCommand;
import lv.javaguru.ee.deliveryagency.core.commands.client.UpdateClientResult;
import lv.javaguru.ee.deliveryagency.core.database.ClientDAO;
import lv.javaguru.ee.deliveryagency.core.database.DeliveryDAO;
import lv.javaguru.ee.deliveryagency.core.domain.Client;
import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
import lv.javaguru.ee.deliveryagency.core.services.DomainCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Viktor on 05/10/2014.
 */
@Component
public class UpdateClientCommandHandler
        implements DomainCommandHandler<UpdateClientCommand, UpdateClientResult> {

    @Autowired
    private DeliveryDAO deliveryDAO;

    @Autowired
    private ClientDAO clientDAO;


    @Override
    public UpdateClientResult execute(UpdateClientCommand command) {
        validateCommand(command);

        Delivery delivery = deliveryDAO.getRequired(command.getDeliveryId());
        Client client = delivery.getClient();

        if(client == null || !client.getClientId().equals(command.getClientId())) {
            throw new IllegalArgumentException("Client id not valid");
        }

        client.setFirstName(command.getFirstName());
        client.setLastName(command.getLastName());
        client.setPhone(command.getPhone());
        client.setEmail(command.getEmail());
        client.setSpecialNotes(command.getSpecialNotes());

        clientDAO.update(client);

        return new UpdateClientResult(client);
    }

    private void validateCommand(UpdateClientCommand command) {
        checkNotNull(command, "UpdateClientCommand must not be null");
        checkNotNull(command.getDeliveryId(), "DeliveryId must not be null");
        checkNotNull(command.getClientId(), "ClientId must not be null");
    }

    @Override
    public Class getCommandType() {
        return UpdateClientCommand.class;
    }
}
