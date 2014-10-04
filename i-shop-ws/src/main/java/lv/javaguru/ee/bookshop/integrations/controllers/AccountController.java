package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.core.CommandExecutor;
import lv.javaguru.ee.bookshop.core.commands.*;
import lv.javaguru.ee.bookshop.core.domain.Account;
import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;
import lv.javaguru.ee.bookshop.integrations.resourses.AccountResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by MumboJumbo on 22/09/14.
 */
@RestController
public class AccountController implements AccountResource {

    @Autowired
    private CommandExecutor commandExecutor;

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/rest/account/")
    public AccountDTO createAccount(@RequestBody AccountDTO accountDTO) {
        CreateAccountCommand command = createAccountCommand(accountDTO);
        CreateAccountResult result = commandExecutor.execute(command);

        Account account = result.getAccount();
        AccountDTO resultDTO = createAccountDTO(account);

        return resultDTO;
    }

    private AccountDTO createAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(account.getAccountId());
        accountDTO.setFirstName(account.getFirstName());
        accountDTO.setLastName(account.getLastName());
        accountDTO.setDateOfBirth(account.getDateOfBirth());
        accountDTO.setEmailAddress(account.getEmailAddress());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setStreet(account.getAddress().getStreet());
        accountDTO.setHouseNumber(account.getAddress().getHouseNumber());
        accountDTO.setBoxNumber(account.getAddress().getBoxNumber());
        accountDTO.setCity(account.getAddress().getCity());
        accountDTO.setPostalCode(account.getAddress().getPostalCode());
        accountDTO.setCountry(account.getAddress().getCountry());

        return accountDTO;
    }

    private CreateAccountCommand createAccountCommand(AccountDTO accountDTO) {
        return new CreateAccountCommand(
                accountDTO.getFirstName(),
                accountDTO.getLastName(),
                accountDTO.getDateOfBirth(),
                accountDTO.getEmailAddress(),
                accountDTO.getUsername(),
                accountDTO.getPassword(),
                accountDTO.getStreet(),
                accountDTO.getHouseNumber(),
                accountDTO.getBoxNumber(),
                accountDTO.getCity(),
                accountDTO.getPostalCode(),
                accountDTO.getCountry()
        );
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/rest/account/{accountId}")
    public AccountDTO getAccount(@PathVariable Long accountId) {
        GetAccountCommand command = new GetAccountCommand(accountId);
        GetAccountResult result = commandExecutor.execute(command);
        Account account = result.getAccount();
        AccountDTO accountDTO = createAccountDTO(account);
        return accountDTO;
    }

//    @RequestMapping(method = RequestMethod.PUT, value = "/rest/account/{accountId}")
//    public ResponseEntity<AccountDTO> UpdateAccount(@RequestBody AccountDTO accountDTO) {
//        UpdateAccountCommand command = updateAccountCommand(accountDTO);
//        UpdateAccountResult result = commandExecutor.execute(command);
//
//        return new ResponseEntity<AccountDTO>(HttpStatus.OK);
//    }
//
//    private UpdateAccountCommand updateAccountCommand(AccountDTO accountDTO) {
//        return new UpdateAccountCommand(
//                accountDTO.getAccountId(),
//                accountDTO.getFirstName(),
//                accountDTO.getLastName(),
//                accountDTO.getDateOfBirth(),
//                accountDTO.getEmailAddress(),
//                accountDTO.getUsername(),
//                accountDTO.getPassword(),
//                accountDTO.getStreet(),
//                accountDTO.getHouseNumber(),
//                accountDTO.getBoxNumber(),
//                accountDTO.getCity(),
//                accountDTO.getPostalCode(),
//                accountDTO.getCountry()
//        );
//    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "/rest/account/{accountId}")
    public void deleteAccount(@PathVariable Long accountId) {
        DeleteAccountCommand command = new DeleteAccountCommand(accountId);
        DeleteAccountResult result = commandExecutor.execute(command);

    }

    @Override
    public AccountDTO updateAccount(Long accountId, AccountDTO accountDTO) throws RestException {
        return null;
    }

}