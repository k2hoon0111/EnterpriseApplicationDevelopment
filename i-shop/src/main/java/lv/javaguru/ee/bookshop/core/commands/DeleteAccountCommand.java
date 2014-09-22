package lv.javaguru.ee.bookshop.core.commands;

/**
 * Created by MumboJumbo on 19/09/14.
 */

public class DeleteAccountCommand implements DomainCommand<DeleteAccountResult> {
    private Long accountId;

    public DeleteAccountCommand(
            Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}