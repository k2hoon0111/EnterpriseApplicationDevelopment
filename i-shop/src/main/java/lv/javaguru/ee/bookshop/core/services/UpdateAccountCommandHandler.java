package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.UpdateAccountCommand;
import lv.javaguru.ee.bookshop.core.commands.UpdateAccountResult;
import lv.javaguru.ee.bookshop.core.database.jpa.JPACRUDOperationDAO;
import lv.javaguru.ee.bookshop.core.domain.Account;
import lv.javaguru.ee.bookshop.core.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by nikoboro on 2014.09.19..
 */

@Component
public class UpdateAccountCommandHandler
        implements DomainCommandHandler<UpdateAccountCommand, UpdateAccountResult> {

    @Autowired
    private JPACRUDOperationDAO jpacrudOperationDAO;

    @Override
    public UpdateAccountResult execute(UpdateAccountCommand command) {
        validateCommand(command);

        Account account = selectAccountEntityFromDB(command);
        jpacrudOperationDAO.update(account);

        return new UpdateAccountResult(account);
    }

    private Account selectAccountEntityFromDB(UpdateAccountCommand command) {
        Address address = new Address();
        address.setStreet(command.getStreet());
        address.setHouseNumber(command.getHouseNumber());
        address.setBoxNumber(command.getBoxNumber());
        address.setBoxNumber(command.getCity());
        address.setPostalCode(command.getPostalCode());
        address.setCountry(command.getCountry());
        address.setCity(command.getCity());

        Account account = jpacrudOperationDAO.getById(Account.class, command.getAccountId());

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

    private void validateCommand(UpdateAccountCommand command) {
        checkNotNull(command, "UpdateAccountCommand must not be null");
        checkNotNull(command.getAccountId(), "Account id  must not be null");
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
        return UpdateAccountCommand.class;
    }

}
