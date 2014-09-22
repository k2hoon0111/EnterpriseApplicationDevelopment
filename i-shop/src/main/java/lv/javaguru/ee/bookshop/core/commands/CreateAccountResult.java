package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.domain.Account;

/**
 * Created by MumboJumbo on 22/09/14.
 */
public class CreateAccountResult implements DomainCommandResult {

    private Account account;

    public CreateAccountResult(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

}