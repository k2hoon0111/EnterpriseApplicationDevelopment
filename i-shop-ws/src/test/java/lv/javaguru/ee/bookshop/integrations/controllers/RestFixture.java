package lv.javaguru.ee.bookshop.integrations.controllers;

import lv.javaguru.ee.bookshop.integrations.Server;
import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
import lv.javaguru.ee.bookshop.integrations.domain.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by Viktor on 16/09/2014.
 */
public class RestFixture {

    private static final String BASE_URL = "http://localhost:" + Server.PORT;
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    /*
    /////////// Delivery methods /////////////
        public static ResponseEntity<DeliveryDTO> createDelivery(DeliveryDTO deliveryDTO) {
            return REST_TEMPLATE.postForEntity(BASE_URL + "/rest/delivery",
                    deliveryDTO, DeliveryDTO.class, new HashMap<String, String>()
            );
        }

        public static ResponseEntity<DeliveryDTO> getDelivery(Long deliveryId) {
            return REST_TEMPLATE.getForEntity(BASE_URL + "/rest/delivery/" + deliveryId,
                    DeliveryDTO.class, new HashMap<String, String>());
        }

        public static DeliveryDTO createDelivery() {
            DeliveryDTO deliveryDTO = new DeliveryDTO();
            ResponseEntity<DeliveryDTO> createDeliveryResponse
                    = RestFixture.createDelivery(deliveryDTO);
            return createDeliveryResponse.getBody();
        }

    ////////////// Client methods ////////////////
        public static ResponseEntity<ClientDTO> createClient(Long deliveryId,
                                                             ClientDTO clientDTO) {
            return REST_TEMPLATE.postForEntity(BASE_URL + "/rest/delivery/" + deliveryId + "/client",
                    clientDTO, ClientDTO.class, new HashMap<String, String>()
            );
        }

        public static ResponseEntity<ClientDTO> getClient(Long deliveryId, Long clientId) {
            return REST_TEMPLATE.getForEntity(BASE_URL + "/rest/delivery/" + deliveryId + "/client/" + clientId,
                    ClientDTO.class, new HashMap<String, String>());
        }
    */
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
        REST_TEMPLATE.delete(BASE_URL + "/rest/book/" + bookId,
                BookDTO.class, new HashMap<String, String>());
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

}
