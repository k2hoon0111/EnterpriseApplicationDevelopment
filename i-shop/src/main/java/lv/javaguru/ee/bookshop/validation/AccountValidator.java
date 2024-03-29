package lv.javaguru.ee.bookshop.validation;

import lv.javaguru.ee.bookshop.core.domain.Account;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 07/09/14
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 */
public class AccountValidator implements Validator {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public boolean supports(Class<?> clazz) {
        return (Account.class).isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "username", "required", new Object[]{"Username"});
        ValidationUtils.rejectIfEmpty(errors, "password", "required", new Object[]{"Password"});
        ValidationUtils.rejectIfEmpty(errors, "email_address", "required", new Object[]{"Email address"});
        ValidationUtils.rejectIfEmpty(errors, "address.street", "required", new Object[]{"Street"});
        ValidationUtils.rejectIfEmpty(errors, "address.city", "required", new Object[]{"City"});
        ValidationUtils.rejectIfEmpty(errors, "address.country", "required", new Object[]{"Country"});

        if (!errors.hasFieldErrors("email_address")) {
            Account account = (Account) target;
            String email = account.getEmailAddress();
            if (!email.matches(EMAIL_PATTERN)) {
                errors.rejectValue("email_address", "invalid");
            }
        }
    }
}