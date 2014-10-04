package lv.javaguru.ee.bookshop.integrations.controllers.fixtures;

import lv.javaguru.ee.bookshop.integrations.PropertiesReader;
import lv.javaguru.ee.bookshop.integrations.domain.*;
import lv.javaguru.ee.bookshop.integrations.resourses.*;

public class RestFixture {
    private static CategoryResource categoryResource = createCategoryResource();
    private static AccountResource accountResource = createAccountResource();
    private static BookResource bookResource = createBookResource();
    private static OrderResource orderResource = createOrderResource();
    private static OrderDetailResource orderDetailResource = createOrderDetailResource();

    /////////// Category methods /////////////
    private static CategoryResource createCategoryResource() {
        PropertiesReader propertiesReader = new PropertiesReader();
        String baseUrl = propertiesReader.getBaseUrl();
        return new CategoryResourceImpl(baseUrl);
    }

    public static CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return categoryResource.createCategory(categoryDTO);
    }

    public static CategoryDTO getCategory(Long categoryId) {
        return categoryResource.getCategory(categoryId);
    }

    public static void deleteCategory(Long categoryId) {
        categoryResource.deleteCategory(categoryId);
    }

    /////////// Account methods /////////////
    private static AccountResource createAccountResource() {
        PropertiesReader propertiesReader = new PropertiesReader();
        String baseUrl = propertiesReader.getBaseUrl();
        return new AccountResourceImpl(baseUrl);
    }

    public static AccountDTO createAccount(AccountDTO accountDTO) {
        return accountResource.createAccount(accountDTO);
    }

    public static AccountDTO getAccount(Long accountId) {
        return accountResource.getAccount(accountId);
    }

    public static void deleteAccount(Long accountId) {
        accountResource.deleteAccount(accountId);
    }

    /////////// Book methods /////////////
    private static BookResource createBookResource() {
        PropertiesReader propertiesReader = new PropertiesReader();
        String baseUrl = propertiesReader.getBaseUrl();
        return new BookResourceImpl(baseUrl);
    }

    public static BookDTO createBook(Long categoryId, BookDTO bookDTO) {
        return bookResource.createBook(categoryId, bookDTO);
    }

    public static BookDTO getBook(Long categoryId, Long bookId) {
        return bookResource.getBook(categoryId, bookId);
    }

    public static void deleteBook(Long bookId) {
        bookResource.deleteBook(bookId);
    }

    /////////// Order methods /////////////
    private static OrderResource createOrderResource() {
        PropertiesReader propertiesReader = new PropertiesReader();
        String baseUrl = propertiesReader.getBaseUrl();
        return new OrderResourceImpl(baseUrl);
    }

    public static OrderDTO createOrder(Long accountId, OrderDTO orderDTO) {
        return orderResource.createOrder(accountId, orderDTO);
    }

    public static OrderDTO getOrder(Long accountId, Long orderId) {
        return orderResource.getOrder(accountId, orderId);
    }

    public static void deleteOrder(Long orderId) {
        orderResource.deleteOrder(orderId);
    }

    /////////// OrderDetail methods /////////////
    private static OrderDetailResource createOrderDetailResource() {
        PropertiesReader propertiesReader = new PropertiesReader();
        String baseUrl = propertiesReader.getBaseUrl();
        return new OrderDetailResourceImpl(baseUrl);
    }

    public static OrderDetailDTO createOrderDetail(Long bookId, Long orderId, OrderDetailDTO orderDetailDTO) {
        return orderDetailResource.createOrderDetail(bookId, orderId, orderDetailDTO);
    }

    public static OrderDetailDTO getOrderDetail(Long bookId, Long orderId, Long orderDetailId) {
        return orderDetailResource.getOrderDetail(bookId, orderId, orderDetailId);
    }

    public static void deleteOrderDetail(Long orderDetailId) {
        orderDetailResource.deleteOrderDetail(orderDetailId);
    }

}
