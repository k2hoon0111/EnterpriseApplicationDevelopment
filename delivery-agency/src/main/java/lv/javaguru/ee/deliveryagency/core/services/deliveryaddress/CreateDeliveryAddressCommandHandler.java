package lv.javaguru.ee.deliveryagency.core.services.deliveryaddress;

import static com.google.common.base.Preconditions.*;

import lv.javaguru.ee.deliveryagency.core.services.DomainCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.javaguru.ee.deliveryagency.core.commands.deliveryaddress.CreateDeliveryAddressCommand;
import lv.javaguru.ee.deliveryagency.core.commands.deliveryaddress.CreateDeliveryAddressResult;
import lv.javaguru.ee.deliveryagency.core.database.DeliveryAddressDAO;
import lv.javaguru.ee.deliveryagency.core.database.DeliveryDAO;
import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
import lv.javaguru.ee.deliveryagency.core.domain.DeliveryAddress;

/**
 * Created by Viktor on 27/07/2014.
 */
@Component
public class CreateDeliveryAddressCommandHandler
        implements DomainCommandHandler<CreateDeliveryAddressCommand, CreateDeliveryAddressResult> {

	@Autowired
	private DeliveryDAO deliveryDAO;

	@Autowired
	private DeliveryAddressDAO deliveryAddressDAO;


	@Override
    public CreateDeliveryAddressResult execute(CreateDeliveryAddressCommand command) {
		validateCommand(command);

		Delivery delivery = deliveryDAO.getById(command.getDeliveryId());
		DeliveryAddress deliveryAddress = createDeliveryAddressEntity(command, delivery);
		deliveryAddressDAO.create(deliveryAddress);

        return new CreateDeliveryAddressResult(deliveryAddress);
    }

	private DeliveryAddress createDeliveryAddressEntity(CreateDeliveryAddressCommand command, Delivery delivery) {
		DeliveryAddress deliveryAddress = new DeliveryAddress();
		deliveryAddress.setDelivery(delivery);
		deliveryAddress.setCity(command.getCity());
		deliveryAddress.setStreet(command.getStreet());
		deliveryAddress.setHouse(command.getHouse());
		deliveryAddress.setFlat(command.getFlat());
		deliveryAddress.setPostIndex(command.getPostIndex());
		return deliveryAddress;
	}

	private void validateCommand(CreateDeliveryAddressCommand command) {
		checkNotNull(command, "CreateDeliveryAddressCommand must not be null");
		checkNotNull(command.getDeliveryId(), "DeliveryId must not be null");
		checkNotNull(command.getCity(), "City must not be null");
		checkNotNull(command.getStreet(), "Street must not be null");
		checkNotNull(command.getHouse(), "House must not be null");
		checkNotNull(command.getFlat(), "Flat must not be null");		
		checkNotNull(command.getPostIndex(), "Post Index must not be null");		
	}

	@Override
    public Class getCommandType() {
        return CreateDeliveryAddressCommand.class;
    }

}
