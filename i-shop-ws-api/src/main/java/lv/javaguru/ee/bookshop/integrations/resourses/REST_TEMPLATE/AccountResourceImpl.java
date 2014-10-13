//package lv.javaguru.ee.bookshop.integrations.resourses.REST_TEMPLATE;
//
//import lv.javaguru.ee.bookshop.integrations.RestException;
//import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//
///**
// * Created by Viktor on 19/09/2014.
// */
//public class AccountResourceImpl implements AccountResource {
//
//    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
//
//    private String baseWebServiceUrl;
//
//
//    public AccountResourceImpl(String baseWebServiceUrl) {
//        this.baseWebServiceUrl = baseWebServiceUrl;
//    }
//
//    @Override
//    public AccountDTO createAccount(AccountDTO accountDTO) throws RestException {
//        try {
//            ResponseEntity<AccountDTO> responseEntity = REST_TEMPLATE.postForEntity(baseWebServiceUrl + CREATE_ACCOUNT_URL,
//                    accountDTO, AccountDTO.class, new HashMap<String, String>()
//            );
//            return responseEntity.getBody();
//        } catch (Throwable e) {
//            e.printStackTrace();
//            throw new RestException(e);
//        }
//    }
//
//    @Override
//    public AccountDTO getAccount(Long accountId) throws RestException {
//        try {
//            String getAccountUrl = GET_ACCOUNT_URL.replace("{accountId}", accountId.toString());
//            ResponseEntity<AccountDTO> responseEntity = REST_TEMPLATE.getForEntity(baseWebServiceUrl + getAccountUrl,
//                    AccountDTO.class, new HashMap<String, String>()
//            );
//            return responseEntity.getBody();
//        } catch (Throwable e) {
//            throw new RestException(e);
//        }
//    }
//
//    @Override
//    public void updateAccount(Long accountId, AccountDTO accountDTO) throws RestException {
//        try {
//            String updateAccountUrl = UPDATE_ACCOUNT_URL.replace("{accountId}", accountId.toString());
//            REST_TEMPLATE.put(baseWebServiceUrl + updateAccountUrl,
//                    accountDTO, AccountDTO.class, new HashMap<String, String>()
//            );
//        } catch (Throwable e) {
//            e.printStackTrace();
//            throw new RestException(e);
//        }
//    }
//
//    @Override
//    public void deleteAccount(Long accountId) throws RestException {
//        try {
//            String deleteAccountUrl = DELETE_ACCOUNT_URL.replace("{accountId}", accountId.toString());
//            REST_TEMPLATE.delete(baseWebServiceUrl + deleteAccountUrl);
//        } catch (Throwable e) {
//            e.printStackTrace();
//            throw new RestException(e);
//        }
//    }
//}
