package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.domain.Account;

/**
 * Created by nikoboro on 2014.09.19..
 */

public class UpdateAccountResult implements DomainCommandResult {

    private Account account;

    public UpdateAccountResult(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}