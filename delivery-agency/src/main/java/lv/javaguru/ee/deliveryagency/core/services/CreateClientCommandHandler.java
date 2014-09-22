package lv.javaguru.ee.deliveryagency.core.services;

import static com.google.common.base.Preconditions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.javaguru.ee.deliveryagency.core.commands.CreateClientCommand;
import lv.javaguru.ee.deliveryagency.core.commands.CreateClientResult;
import lv.javaguru.ee.deliveryagency.core.database.ClientDAO;
import lv.javaguru.ee.deliveryagency.core.database.DeliveryDAO;
import lv.javaguru.ee.deliveryagency.core.domain.Client;
import lv.javaguru.ee.deliveryagency.core.domain.Delivery;

@Component
public class CreateClientCommandHandler
		implements DomainCommandHandler<CreateClientCommand, CreateClientResult> {

	@Autowired
	private DeliveryDAO deliveryDAO;

	@Autowired
	private ClientDAO clientDAO;


	@Override
	public CreateClientResult execute(CreateClientCommand command) {
		validateCommand(command);

		Delivery delivery = deliveryDAO.getById(command.getDeliveryId());
		Client client = createClientEntityFromCommand(command, delivery);
		clientDAO.create(client);
		
		return new CreateClientResult(client);
	}

	private Client createClientEntityFromCommand(CreateClientCommand command, Delivery delivery) {
		Client client = new Client();
		client.setFirstName(command.getFirstName());
		client.setLastName(command.getLastName());
		client.setDelivery(delivery);
		client.setPhone(command.getPhone());
		client.setEmail(command.getEmail());
		client.setSpecialNotes(command.getSpecialNotes());
		return client;
	}

	private void validateCommand(CreateClientCommand command) {
		checkNotNull(command, "CreateClientCommand must not be null");
		checkNotNull(command.getDeliveryId(), "DeliveryId must not be null");
		checkNotNull(command.getFirstName(), "First name must not be null");
		checkNotNull(command.getLastName(), "Last name must not be null");
		checkNotNull(command.getPhone(), "Phone must not be null");
	}

	@Override
	public Class getCommandType() {
		return CreateClientCommand.class;
	}
	
}
