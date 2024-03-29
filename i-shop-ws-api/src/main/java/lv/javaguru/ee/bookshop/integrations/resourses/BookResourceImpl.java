package lv.javaguru.ee.bookshop.integrations.resourses;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class BookResourceImpl implements BookResource {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private String baseWebServiceUrl;


    public BookResourceImpl(String baseWebServiceUrl) {
        this.baseWebServiceUrl = baseWebServiceUrl;
    }

    @Override
    public BookDTO createBook(Long categoryId, BookDTO bookDTO) throws RestException {
        try {
            ResponseEntity<BookDTO> responseEntity = REST_TEMPLATE.postForEntity(baseWebServiceUrl + CREATE_BOOK_URL.replace("{categoryId}", categoryId.toString()),
                    bookDTO, BookDTO.class, new HashMap<String, String>()
            );
            return responseEntity.getBody();
        } catch (Throwable e) {
            throw new RestException(e);
        }
    }

    @Override
    public BookDTO getBook(Long categoryId, Long bookId) throws RestException {
        try {
            String getBookUrl = GET_BOOK_URL.replace("{categoryId}", categoryId.toString()).replace("{bookId}", bookId.toString());
            ResponseEntity<BookDTO> responseEntity = REST_TEMPLATE.getForEntity(baseWebServiceUrl + getBookUrl,
                    BookDTO.class, new HashMap<String, String>()
            );
            return responseEntity.getBody();
        } catch (Throwable e) {
            throw new RestException(e);
        }
    }

    @Override
    public void updateBook(Long categoryId, Long bookId, BookDTO bookDTO) throws RestException {
        try {
            String updateBookUrl = UPDATE_BOOK_URL.replace("{categoryId}", categoryId.toString()).
                    replace("{bookId}", bookId.toString());
            REST_TEMPLATE.put(baseWebServiceUrl + updateBookUrl,
                    bookDTO, BookDTO.class, new HashMap<String, String>()
            );
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RestException(e);
        }
    }

    @Override
    public void deleteBook(Long categoryId, Long bookId) throws RestException {
        try {
            String deleteBookUrl = DELETE_BOOK_URL.replace("{categoryId}", categoryId.toString()).
                    replace("{bookId}", bookId.toString());
            REST_TEMPLATE.delete(baseWebServiceUrl + deleteBookUrl);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RestException(e);
        }
    }
}
