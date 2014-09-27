//package lv.javaguru.ee.bookshop.integrations.controllers;
//
//import junit.framework.TestCase;
//import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
//import lv.javaguru.ee.bookshop.core.domain.Book;
//import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
//import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
//import org.hamcrest.MatcherAssert;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.HttpClientErrorException;
//
//import java.math.BigDecimal;
//
//import static org.hamcrest.CoreMatchers.is;
//
//public class GetBookTest extends EmbeddedJettyTest {
//
//    @Test
//    public void testGetBook() {
//        BookDTO bookDTO = new BookDTO();
//        bookDTO.setTitle("Test title");
//        bookDTO.setDescription("Test des");
//        bookDTO.setPrice(new BigDecimal("22.20"));
//        bookDTO.setYear(2003);
//        bookDTO.setAuthor("Test author");
//        bookDTO.setIsbn("19384601239756");
//
//        ResponseEntity<BookDTO> createBookResponse
////                = RestFixture.createBook(createDeliveryDTO.getDeliveryId(), bookDTO);
//                = RestFixture.createBook(Long.valueOf(26), bookDTO);
//        BookDTO createBookDTO = createBookResponse.getBody();
//
//        Long bookId = createBookDTO.getBookId();
//
//        ResponseEntity<BookDTO> getBookResponse
//                = RestFixture.getBook(bookId);
//
//        BookDTO getBookDTO = getBookResponse.getBody();
//        MatcherAssert.assertThat(getBookDTO.getBookId(), is(bookId));
//        MatcherAssert.assertThat(getBookDTO.getTitle(), is("Test title"));
//        MatcherAssert.assertThat(getBookDTO.getIsbn(), is("19384601239756"));
//    }
//
//    @Test
//    public void testGetOrderWithWrongId() {
//        Long max = Long.MAX_VALUE;
//        try {
//            RestFixture.getBook(max);
//        } catch (HttpClientErrorException e) {
//            TestCase.assertEquals("Entity " + Book.class.getName() + " not found by id " + max, e.getResponseBodyAsString());
//            Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, e.getStatusCode());
//        }
//    }
//
//}
