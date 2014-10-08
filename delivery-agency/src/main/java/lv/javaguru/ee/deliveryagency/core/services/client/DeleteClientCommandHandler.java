package lv.javaguru.ee.deliveryagency.core.services.client;

import lv.javaguru.ee.deliveryagency.core.commands.client.DeleteClientCommand;
import lv.javaguru.ee.deliveryagency.core.commands.client.DeleteClientResult;
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
public class DeleteClientCommandHandler
        implements DomainCommandHandler<DeleteClientCommand, DeleteClientResult> {

    @Autowired
    private DeliveryDAO deliveryDAO;

    @Autowired
    private ClientDAO clientDAO;


    @Override
    public DeleteClientResult execute(DeleteClientCommand command) {
        validateCommand(command);
        Delivery delivery = deliveryDAO.getRequired(command.getDeliveryId());
        Client client = delivery.getClient();

        if(client == null || !client.getClientId().equals(command.getClientId())) {
            throw new IllegalArgumentException("Client id not valid");
        }

        delivery.setClient(null);
        deliveryDAO.update(delivery);
        clientDAO.delete(client);
        return new DeleteClientResult(client);
    }

    private void validateCommand(DeleteClientCommand command) {
        checkNotNull(command, "DeleteClientCommand must not be null");
        checkNotNull(command.getDeliveryId(), "DeliveryId must not be null");
        checkNotNull(command.getClientId(), "ClientId must not be null");
    }


    @Override
    public Class getCommandType() {
        return DeleteClientCommand.class;
    }
}
