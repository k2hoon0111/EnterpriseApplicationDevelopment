package lv.javaguru.ee.bookshop.integrations.resourses;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;

public interface AccountResource {

	static final String CREATE_ACCOUNT_URL = "/rest/account/";
	static final String ACCOUNT_URL = "/rest/account/{accountId}";

	AccountDTO createAccount(AccountDTO accountDTO) throws RestException;

	AccountDTO getAccount(Long accountId) throws RestException;

    AccountDTO updateAccount(Long accountId, AccountDTO accountDTO) throws RestException;

    AccountDTO deleteAccount(Long accountId) throws RestException;

}
