package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.DeleteAccountCommand;
import lv.javaguru.ee.bookshop.core.commands.DeleteAccountResult;
import lv.javaguru.ee.bookshop.core.database.jpa.JPACRUDOperationDAO;
import lv.javaguru.ee.bookshop.core.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by MumboJumbo on 19/09/14.
 */

@Component
public class DeleteAccountCommandHandler
        implements DomainCommandHandler<DeleteAccountCommand, DeleteAccountResult> {

    @Autowired
    private JPACRUDOperationDAO jpacrudOperationDAO;

    @Override
    public DeleteAccountResult execute(DeleteAccountCommand command) {
        validateCommand(command);
        Account account = selectAccountEntityFromDB(command);
        jpacrudOperationDAO.delete(account);

        return new DeleteAccountResult(account);
    }

    private Account selectAccountEntityFromDB(DeleteAccountCommand command) {
        Account account = jpacrudOperationDAO.getById(Account.class, command.getAccountId());
        return account;
    }

    private void validateCommand(DeleteAccountCommand command) {
        checkNotNull(command, "DeleteAccountCommand must not be null");
        checkNotNull(command.getAccountId(), "Account id  must not be null");
    }

    @Override
    public Class getCommandType() {
        return DeleteAccountCommand.class;
    }

}
