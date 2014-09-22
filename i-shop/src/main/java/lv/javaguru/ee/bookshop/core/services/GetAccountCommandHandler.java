package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.GetAccountCommand;
import lv.javaguru.ee.bookshop.core.commands.GetAccountResult;
import lv.javaguru.ee.bookshop.core.database.jpa.JPACRUDOperationDAO;
import lv.javaguru.ee.bookshop.core.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;


@Component
public class GetAccountCommandHandler
        implements DomainCommandHandler<GetAccountCommand, GetAccountResult> {

    @Autowired
    private JPACRUDOperationDAO jpacrudOperationDAO;


    @Override
    public GetAccountResult execute(GetAccountCommand command) {
        validateCommand(command);
        Account account = jpacrudOperationDAO.getById(Account.class, command.getAccountId());

        if (account == null || !account.getAccountId().equals(command.getAccountId())) {
            throw new RuntimeException("Account id not valid");
        }

        return new GetAccountResult(account);
    }

    private void validateCommand(GetAccountCommand command) {
        checkNotNull(command, "GetAccountCommand must not be null");
        checkNotNull(command.getAccountId(), "Account id must not be null");
    }

    @Override
    public Class getCommandType() {
        return GetAccountCommand.class;
    }
}
