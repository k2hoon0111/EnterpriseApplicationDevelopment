package lv.javaguru.ee.bookshop.validation;

import lv.javaguru.ee.bookshop.core.domain.Address;
import lv.javaguru.ee.bookshop.core.domain.Order;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 07/09/14
 * Time: 13:27
 * To change this template use File | Settings | File Templates.
 */
public class OrderValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return (Order.class).isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Order order = (Order) target;
        validateAddress(order.getShippingAddress(), errors, "shippingAddress");
        if (!order.isBillingSameAsShipping()) {
            validateAddress(order.getShippingAddress(), errors, "billingAddress");
        }

    }

    private void validateAddress(Address address, Errors errors, String type) {
        ValidationUtils.rejectIfEmpty(errors, type + ".street", "required", new Object[] { "Street" });
        ValidationUtils.rejectIfEmpty(errors, type + ".city", "required", new Object[] { "City" });
        ValidationUtils.rejectIfEmpty(errors, type + ".country", "required", new Object[] { "Country" });

    }

}