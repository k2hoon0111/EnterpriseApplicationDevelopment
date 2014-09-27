package lv.javaguru.ee.bookshop.integrations.resourses;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;

public interface AccountResource {

	static final String CREATE_ACCOUNT_URL = "/rest/account/";
	static final String GET_ACCOUNT_URL = "/rest/account/{accountId}";
    static final String UPDATE_ACCOUNT_URL = "/rest/account/{accountId}";
    static final String DELETE_ACCOUNT_URL = "/rest/account/{accountId}";


	AccountDTO createAccount(AccountDTO accountDTO) throws RestException;

	AccountDTO getAccount(Long accountId) throws RestException;

    AccountDTO updateAccount(Long accountId, AccountDTO accountDTO) throws RestException;

    void deleteAccount(Long accountId) throws RestException;

}
