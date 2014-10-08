package lv.javaguru.ee.bookshop.integrations.resourses;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Created by Viktor on 19/09/2014.
 */
public class AccountResourceImpl implements AccountResource {

    private String baseWebServiceUrl;
    private Client client;

    public AccountResourceImpl(String baseWebServiceUrl, Client client) {
        this.baseWebServiceUrl = baseWebServiceUrl;
        this.client = client;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) throws RestException {
        try {
            WebTarget target = client.target(baseWebServiceUrl);
            return target.request(MediaType.APPLICATION_XML)
                    .post(Entity.entity(accountDTO, MediaType.APPLICATION_XML), AccountDTO.class);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RestException(e);
        }
    }

    @Override
    public AccountDTO getAccount(Long accountId) throws RestException {
        try {
            String getAccountUrl = ACCOUNT_URL.replace("{accountId}", accountId.toString());
            WebTarget target = client.target(getAccountUrl);
            return target.request(MediaType.APPLICATION_XML).get(AccountDTO.class);
        } catch (Throwable e) {
            throw new RestException(e);
        }
    }

    @Override
    public AccountDTO updateAccount(Long accountId, AccountDTO accountDTO) throws RestException {
        try {
            String updateAccountUrl = ACCOUNT_URL.replace("{accountId}", accountId.toString());
            WebTarget target = client.target(updateAccountUrl);
            return target.request(MediaType.APPLICATION_XML)
                    .put(Entity.entity(accountDTO, MediaType.APPLICATION_XML), AccountDTO.class);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RestException(e);
        }
    }

    @Override
    public AccountDTO deleteAccount(Long accountId) throws RestException {
        try {
            String deleteAccountUrl = ACCOUNT_URL.replace("{accountId}", accountId.toString());
            WebTarget target = client.target(deleteAccountUrl);
            return target.request(MediaType.APPLICATION_XML).delete(AccountDTO.class);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RestException(e);
        }
    }
}
