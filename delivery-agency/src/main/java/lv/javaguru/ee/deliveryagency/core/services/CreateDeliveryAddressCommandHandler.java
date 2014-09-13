package lv.javaguru.ee.deliveryagency.core.services;

import org.springframework.stereotype.Component;

import lv.javaguru.ee.deliveryagency.core.DomainCommandService;
import lv.javaguru.ee.deliveryagency.core.commands.CreateDeliveryAddressCommand;
import lv.javaguru.ee.deliveryagency.core.commands.CreateDeliveryAddressResult;

/**
 * Created by Viktor on 27/07/2014.
 */
@Component
public class CreateDeliveryAddressCommandHandler
        implements DomainCommandService<CreateDeliveryAddressCommand, CreateDeliveryAddressResult> {


    @Override
    public CreateDeliveryAddressResult execute(CreateDeliveryAddressCommand command) {


        return null;
    }

    @Override
    public Class getCommandType() {
        return CreateDeliveryAddressCommand.class;
    }

}
