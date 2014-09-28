package lv.javaguru.ee.bookshop.integrations.controllers.fixtures;

import lv.javaguru.ee.bookshop.integrations.PropertiesReader;
import lv.javaguru.ee.bookshop.integrations.domain.*;
import lv.javaguru.ee.bookshop.integrations.resourses.*;

/**
 * Created by Viktor on 16/09/2014.
 */
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

     /*

    private static final String BASE_URL = "http://localhost:" + Server.PORT;
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    ////////////// Book methods ////////////////
    public static ResponseEntity<BookDTO> createBook(Long categoryId,
                                                     BookDTO bookDTO) {
        return REST_TEMPLATE.postForEntity(BASE_URL + "/rest/category/" + categoryId + "/book",
                bookDTO, BookDTO.class, new HashMap<String, String>()
        );
    }

    public static void updateBook(BookDTO bookDTO) {
        REST_TEMPLATE.put(BASE_URL + "/rest/book/" + bookDTO.getBookId(),
                bookDTO, BookDTO.class, new HashMap<String, String>()
        );
    }

    public static ResponseEntity<BookDTO> getBook(Long bookId) {
        return REST_TEMPLATE.getForEntity(BASE_URL + "/rest/book/" + bookId,
                BookDTO.class, new HashMap<String, String>());
    }

    public static void deleteBook(Long bookId) {
        REST_TEMPLATE.delete(BASE_URL + "/rest/book/" + bookId);
    }

    ////////////// Category methods ////////////////
    public static ResponseEntity<CategoryDTO> createCategory(CategoryDTO categoryDTO) {
        return REST_TEMPLATE.postForEntity(BASE_URL + "/rest/category/",
                categoryDTO, CategoryDTO.class, new HashMap<String, String>()
        );
    }

    public static ResponseEntity<CategoryDTO> getCategory(Long categoryId) {
        return REST_TEMPLATE.getForEntity(BASE_URL + "/rest/category/" + categoryId,
                CategoryDTO.class, new HashMap<String, String>());
    }

    public static void updateCategory(CategoryDTO categoryDTO) {
        REST_TEMPLATE.put(BASE_URL + "/rest/category/" + categoryDTO.getCategoryId(),
                categoryDTO, CategoryDTO.class, new HashMap<String, String>()
        );
    }

    public static void deleteCategory(Long categoryId) {
        REST_TEMPLATE.delete(BASE_URL + "/rest/category/" + categoryId);
    }

    ////////////// Order methods ////////////////
    public static ResponseEntity<OrderDTO> createOrder(OrderDTO orderDTO) {
        return REST_TEMPLATE.postForEntity(BASE_URL + "/rest/order/",
                orderDTO, OrderDTO.class, new HashMap<String, String>()
        );
    }

    public static ResponseEntity<OrderDTO> getOrder(Long orderId) {
        return REST_TEMPLATE.getForEntity(BASE_URL + "/rest/order/" + orderId,
                OrderDTO.class, new HashMap<String, String>());
    }

    public static void updateOrder(OrderDTO orderDTO) {
        REST_TEMPLATE.put(BASE_URL + "/rest/order/" + orderDTO.getOrderId(),
                orderDTO, OrderDTO.class, new HashMap<String, String>()
        );
    }

    public static void deleteOrder(Long orderId) {
        REST_TEMPLATE.delete(BASE_URL + "/rest/order/" + orderId);
    }

    ////////////// OrderDetail methods ////////////////
    public static ResponseEntity<OrderDetailDTO> createOrderDetail(OrderDetailDTO orderDetailDTO) {
        return REST_TEMPLATE.postForEntity(BASE_URL + "/rest/orderdetail/",
                orderDetailDTO, OrderDetailDTO.class, new HashMap<String, String>()
        );
    }

    public static ResponseEntity<OrderDetailDTO> getOrderDetail(Long orderDetailId) {
        return REST_TEMPLATE.getForEntity(BASE_URL + "/rest/orderdetail/" + orderDetailId,
                OrderDetailDTO.class, new HashMap<String, String>());
    }

    public static void deleteOrderDetail(Long orderDetailId) {
        REST_TEMPLATE.delete(BASE_URL + "/rest/orderdetail/" + orderDetailId);
    }

    public static void updateOrderDetail(OrderDetailDTO orderDetailDTO) {
        REST_TEMPLATE.put(BASE_URL + "/rest/orderdetail/" + orderDetailDTO.getOrderDetailId(),
                orderDetailDTO, OrderDetailDTO.class, new HashMap<String, String>()
        );
    }

    ////////////// Account methods ////////////////
    public static ResponseEntity<AccountDTO> createAccount(AccountDTO accountDTO) {
        return REST_TEMPLATE.postForEntity(BASE_URL + "/rest/account/",
                accountDTO, AccountDTO.class, new HashMap<String, String>()
        );
    }

    public static ResponseEntity<AccountDTO> getAccount(Long accountId) {
        return REST_TEMPLATE.getForEntity(BASE_URL + "/rest/account/" + accountId,
                AccountDTO.class, new HashMap<String, String>());
    }

    public static void deleteAccount(Long accountId) {
        REST_TEMPLATE.delete(BASE_URL + "/rest/account/" + accountId);
    }

    public static void updateAccount(AccountDTO accountDTO) {
        REST_TEMPLATE.put(BASE_URL + "/rest/account/" + accountDTO.getAccountId(),
                accountDTO, AccountDTO.class, new HashMap<String, String>()
        );
    }
    */
}
