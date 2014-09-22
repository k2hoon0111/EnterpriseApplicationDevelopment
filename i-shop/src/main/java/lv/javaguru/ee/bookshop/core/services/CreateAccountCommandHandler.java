package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.CreateAccountCommand;
import lv.javaguru.ee.bookshop.core.commands.CreateAccountResult;
import lv.javaguru.ee.bookshop.core.database.jpa.JPACRUDOperationDAO;
import lv.javaguru.ee.bookshop.core.domain.Account;
import lv.javaguru.ee.bookshop.core.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by MumboJumbo on 22/09/14.
 */
@Component
public class CreateAccountCommandHandler
        implements DomainCommandHandler<CreateAccountCommand, CreateAccountResult> {

    @Autowired
    private JPACRUDOperationDAO jpacrudOperationDAO;

    @Override
    public CreateAccountResult execute(CreateAccountCommand command) {
        validateCommand(command);

        Account account = createAccountEntityFromCommand(command);
        jpacrudOperationDAO.create(account);

        return new CreateAccountResult(account);
    }

    private Account createAccountEntityFromCommand(CreateAccountCommand command) {
        Address address = new Address();
        address.setStreet(command.getStreet());
        address.setHouseNumber(command.getHouseNumber());
        address.setBoxNumber(command.getBoxNumber());
        address.setBoxNumber(command.getCity());
        address.setPostalCode(command.getPostalCode());
        address.setCountry(command.getCountry());
        address.setCity(command.getCity());

        Account account = new Account();


        account.setFirstName(command.getFirstName());
        account.setLastName(command.getLastName());
        account.setDateOfBirth(command.getDateOfBirth());
        account.setEmailAddress(command.getEmailAddress());
        account.setUsername(command.getUsername());
        account.setAddress(address);
        account.setPassword(command.getPassword());
        account.setPassword(command.getPassword());
        account.setPassword(command.getPassword());
        account.setPassword(command.getPassword());

        return account;
    }

    private void validateCommand(CreateAccountCommand command) {
        checkNotNull(command, "CreateAccountCommand must not be null");
        checkNotNull(command.getFirstName(), "First name number must not be null");
        checkNotNull(command.getLastName(), "Last name must not be null");
        checkNotNull(command.getDateOfBirth(), "Date of birth must not be null");
        checkNotNull(command.getEmailAddress(), "Email must not be null");
        checkNotNull(command.getUsername(), "User name must not be null");
        checkNotNull(command.getPassword(), "Password must not be null");
        checkNotNull(command.getStreet(), "Street must not be null");
        checkNotNull(command.getHouseNumber(), "House number must not be null");
        checkNotNull(command.getBoxNumber(), "Box number must not be null");
        checkNotNull(command.getCity(), "City must not be null");
        checkNotNull(command.getPostalCode(), "Postal must not be null");
        checkNotNull(command.getCountry(), "Country must not be null");
    }

    @Override
    public Class getCommandType() {
        return CreateAccountCommand.class;
    }

}