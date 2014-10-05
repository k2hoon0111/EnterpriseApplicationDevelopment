package lv.javaguru.ee.bookshop.integrations.resourses;

import lv.javaguru.ee.bookshop.integrations.RestException;
import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;

public interface BookResource {

	static final String CREATE_BOOK_URL = "/rest/category/{categoryId}/book";
	static final String GET_BOOK_URL = "/rest/category/{categoryId}/book/{bookId}";
    static final String UPDATE_BOOK_URL = "/rest/category/{categoryId}/book/{bookId}";
    static final String DELETE_BOOK_URL = "/rest/category/{categoryId}/book/{bookId}";


	BookDTO createBook(Long categoryId, BookDTO bookDTO) throws RestException;

	BookDTO getBook(Long categoryId, Long bookId) throws RestException;

    void updateBook(Long categoryId, Long bookId, BookDTO bookDTO) throws RestException;

    void deleteBook(Long categoryId, Long bookId) throws RestException;

}
