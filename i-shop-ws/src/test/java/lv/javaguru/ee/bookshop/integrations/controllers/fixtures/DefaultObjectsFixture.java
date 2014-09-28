package lv.javaguru.ee.bookshop.integrations.controllers.fixtures;

import lv.javaguru.ee.bookshop.integrations.domain.AccountDTO;
import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import lv.javaguru.ee.bookshop.integrations.domain.OrderDTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by MumboJumbo on 28/09/14.
 */
public class DefaultObjectsFixture {

    public static AccountDTO createDefaultAccount() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setFirstName("First");
        accountDTO.setLastName("Last");
        accountDTO.setDateOfBirth(new Date());
        accountDTO.setEmailAddress("me@gmail.com");
        accountDTO.setUsername("user");
        accountDTO.setPassword("pass");
        accountDTO.setStreet("street");
        accountDTO.setHouseNumber("House");
        accountDTO.setBoxNumber("box");
        accountDTO.setCity("City");
        accountDTO.setPostalCode("postal");
        accountDTO.setCountry("country");

        return accountDTO;
    }

    public static CategoryDTO createDefaultCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("C++");

        return categoryDTO;
    }

    public static BookDTO createDefaultBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Test title");
        bookDTO.setDescription("Test des");
        bookDTO.setPrice(new BigDecimal("22.20"));
        bookDTO.setYear(2003);
        bookDTO.setAuthor("Test author");
        bookDTO.setIsbn("19384601239756");

        return bookDTO;
    }

    public static OrderDTO createDefaultOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setShippingStreet("Street");
        orderDTO.setShippingHouseNumber("6");
        orderDTO.setShippingBoxNumber("4");
        orderDTO.setShippingCity("City");
        orderDTO.setShippingPostalCode("Postal");
        orderDTO.setShippingCountry("Country");
        orderDTO.setBillingStreet("Street");
        orderDTO.setBillingHouseNumber("6");
        orderDTO.setBillingBoxNumber("4");
        orderDTO.setBillingCity("City");
        orderDTO.setBillingoPostalCode("666");
        orderDTO.setBillingCountry("Country");
        orderDTO.setBillingSameAsShipping(false);
        orderDTO.setDeliveryDate(new Date());
        orderDTO.setOrderDate(new Date());

        return orderDTO;
    }
}
