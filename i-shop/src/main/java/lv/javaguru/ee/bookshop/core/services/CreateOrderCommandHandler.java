package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.CreateOrderCommand;
import lv.javaguru.ee.bookshop.core.commands.CreateOrderResult;
import lv.javaguru.ee.bookshop.core.database.jpa.JPACRUDOperationDAO;
import lv.javaguru.ee.bookshop.core.domain.Account;
import lv.javaguru.ee.bookshop.core.domain.Address;
import lv.javaguru.ee.bookshop.core.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class CreateOrderCommandHandler
        implements DomainCommandHandler<CreateOrderCommand, CreateOrderResult> {

    @Autowired
    private JPACRUDOperationDAO jpacrudOperationDAO;

    @Override
    public CreateOrderResult execute(CreateOrderCommand command) {
        validateCommand(command);

        Account account = jpacrudOperationDAO.getById(Account.class, command.getAccountId());
        Order order = createOrderEntityFromCommand(command, account);
        jpacrudOperationDAO.create(order);

        return new CreateOrderResult(order);
    }

    private Order createOrderEntityFromCommand(CreateOrderCommand command, Account account) {
        Address billingAddress = new Address();
        billingAddress.setBoxNumber(command.getBillingBoxNumber());
        billingAddress.setCity(command.getBillingCity());
        billingAddress.setCountry(command.getBillingCountry());
        billingAddress.setHouseNumber(command.getBillingHouseNumber());
        billingAddress.setPostalCode(command.getBillingoPostalCode());
        billingAddress.setStreet(command.getBillingStreet());

        Address shippingAddress = new Address();
        shippingAddress.setBoxNumber(command.getShippingBoxNumber());
        shippingAddress.setCity(command.getShippingCity());
        shippingAddress.setCountry(command.getShippingCountry());
        shippingAddress.setHouseNumber(command.getShippingHouseNumber());
        shippingAddress.setPostalCode(command.getShippingPostalCode());
        shippingAddress.setStreet(command.getShippingStreet());

        Order order = new Order();
        order.setAccount(account);
        order.setBillingAddress(billingAddress);
        order.setShippingAddress(shippingAddress);
        order.setBillingSameAsShipping(command.isBillingSameAsShipping());
        order.setOrderDate(command.getOrderDate());
        order.setDeliveryDate(command.getDeliveryDate());

        return order;
    }

    private void validateCommand(CreateOrderCommand command) {
        checkNotNull(command, "CreateOrderCommand must not be null");

        checkNotNull(command.getAccountId(), "Account id must not be null");

        checkNotNull(command.getBillingBoxNumber(), "Billing box number must not be null");
        checkNotNull(command.getBillingCity(), "Billing city must not be null");
        checkNotNull(command.getBillingCountry(), "Billing country must not be null");
        checkNotNull(command.getBillingHouseNumber(), "Billing house number must not be null");
        checkNotNull(command.getBillingoPostalCode(), "Billing postal code must not be null");
        checkNotNull(command.getBillingStreet(), "Billing street must not be null");

        checkNotNull(command.getAccountId(), "Account id must not be null");
        checkNotNull(command.getShippingBoxNumber(), "Billing box number must not be null");
        checkNotNull(command.getShippingCity(), "Billing city must not be null");
        checkNotNull(command.getShippingCountry(), "Billing country must not be null");
        checkNotNull(command.getShippingHouseNumber(), "Billing house number must not be null");
        checkNotNull(command.getShippingPostalCode(), "Billing postal code must not be null");
        checkNotNull(command.getShippingStreet(), "Billing street must not be null");

        checkNotNull(command.isBillingSameAsShipping(), "Billing same must not be null");

        checkNotNull(command.getOrderDate(), "Billing same must not be null");
        checkNotNull(command.getDeliveryDate(), "Billing same must not be null");
    }

    @Override
    public Class getCommandType() {
        return CreateOrderCommand.class;
    }

}
