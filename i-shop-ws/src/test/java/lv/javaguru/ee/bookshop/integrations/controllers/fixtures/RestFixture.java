package lv.javaguru.ee.bookshop.integrations.controllers.fixtures;

import lv.javaguru.ee.bookshop.integrations.Server;
import lv.javaguru.ee.bookshop.integrations.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by Viktor on 16/09/2014.
 */
public class RestFixture {



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
}
