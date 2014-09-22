package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.domain.Account;

/**
 * Created by MumboJumbo on 19/09/14.
 */

public class DeleteAccountResult implements DomainCommandResult {

    private Account account;

    public DeleteAccountResult(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

}