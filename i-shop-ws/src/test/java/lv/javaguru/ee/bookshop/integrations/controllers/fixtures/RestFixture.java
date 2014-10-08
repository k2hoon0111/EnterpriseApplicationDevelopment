package lv.javaguru.ee.bookshop.integrations.controllers.fixtures;

import lv.javaguru.ee.bookshop.integrations.PropertiesReader;
import lv.javaguru.ee.bookshop.integrations.domain.*;
import lv.javaguru.ee.bookshop.integrations.resourses.*;
import lv.javaguru.ee.bookshop.integrations.resourses.REST_TEMPLATE.AccountResource;
import lv.javaguru.ee.bookshop.integrations.resourses.REST_TEMPLATE.AccountResourceImpl;

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

    public static void updateCategory(Long categoryId, CategoryDTO categoryDTO) {

        categoryResource.updateCategory(categoryId, categoryDTO);
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

    public static AccountDTO deleteAccount(Long accountId) {
        return accountResource.deleteAccount(accountId);
    }

    public static AccountDTO updateAccount(Long accountId, AccountDTO accountDTO) {

        return accountResource.updateAccount(accountId, accountDTO);
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

    public static void deleteBook(Long categoryId, Long bookId) {
        bookResource.deleteBook(categoryId, bookId);
    }

    public static void updateBook(Long categoryId, Long bookId, BookDTO bookDTO) {

        bookResource.updateBook(categoryId, bookId, bookDTO);
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

    public static void deleteOrder(Long accountId, Long orderId) {
        orderResource.deleteOrder(accountId, orderId);
    }

    public static void updateOrder(Long accountId, Long orderId, OrderDTO orderDTO) {

        orderResource.updateOrder(accountId, orderId, orderDTO);
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

    public static void deleteOrderDetail(Long bookId, Long orderId, Long orderDetailId) {
        orderDetailResource.deleteOrderDetail(bookId, orderId, orderDetailId);
    }

    public static void updateOrderDetail(Long bookId, Long orderId, Long orderDetailId, OrderDetailDTO orderDetailDTO) {

        orderDetailResource.updateOrderDetail(bookId, orderId, orderDetailId, orderDetailDTO);
    }

}
