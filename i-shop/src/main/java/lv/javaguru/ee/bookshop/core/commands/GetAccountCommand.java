package lv.javaguru.ee.bookshop.core.commands;

/**
 * Created by MumboJumbo on 22/09/14.
 */
public class GetAccountCommand implements DomainCommand<GetAccountResult> {

    private Long accountId;

    public GetAccountCommand(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountId() {
        return accountId;
    }

}
