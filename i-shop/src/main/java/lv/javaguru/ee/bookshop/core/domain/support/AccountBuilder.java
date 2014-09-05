package lv.javaguru.ee.bookshop.core.domain.support;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import lv.javaguru.ee.bookshop.core.domain.Account;
import lv.javaguru.ee.bookshop.core.domain.Address;
import lv.javaguru.ee.bookshop.core.domain.Permission;
import lv.javaguru.ee.bookshop.core.domain.Role;


/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
@Component
public class AccountBuilder extends EntityBuilder<Account> {

    @Override
    void initProduct() {
        this.product = new Account();
    }

    public AccountBuilder credentials(String username, String password) {
        this.product.setUsername(username);
        this.product.setPassword(DigestUtils.sha256Hex(password + "{" + username + "}"));
        return this;
    }

    public AccountBuilder address(String city, String postalCode, String street, String houseNumber, String boxNumber,
            String country) {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setHouseNumber(houseNumber);
        address.setPostalCode(postalCode);
        address.setBoxNumber(boxNumber);
        address.setCountry(country);

        this.product.setAddress(address);
        return this;
    }

    public AccountBuilder roleWithPermissions(Role role, Permission... permissions) {
        this.product.getRoles().add(role);

        for (Permission permission : permissions) {
            role.getPermissions().add(permission);
        }
        return this;
    }

    public AccountBuilder email(String email) {
        this.product.setEmailAddress(email);
        return this;
    }

    public AccountBuilder name(String firstName, String lastName) {
        this.product.setFirstName(firstName);
        this.product.setLastName(lastName);
        return this;
    }

    @Override
    Account assembleProduct() {
        return this.product;
    }
}