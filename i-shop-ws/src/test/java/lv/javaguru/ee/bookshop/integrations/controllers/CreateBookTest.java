//package lv.javaguru.ee.bookshop.integrations.controllers;
//
//import lv.javaguru.ee.bookshop.integrations.controllers.fixtures.RestFixture;
//import lv.javaguru.ee.bookshop.integrations.domain.BookDTO;
//import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
//import org.hamcrest.MatcherAssert;
//import org.junit.Test;
//import org.springframework.http.ResponseEntity;
//
//import java.math.BigDecimal;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//
//public class CreateBookTest extends EmbeddedJettyTest {
//
//    @Test
//    public void testCreateBook() {
////        CategoryDTO createCategoryDTO = RestFixture.createCategory();
////        assertThat(createBookDTO.getBookId(), is(notNullValue()));
//
//        BookDTO bookDTO = new BookDTO();
//
//        bookDTO.setTitle("Test title");
//        bookDTO.setDescription("Test des");
//        bookDTO.setPrice(new BigDecimal("22.20"));
//        bookDTO.setYear(2003);
//        bookDTO.setAuthor("Test author");
//        bookDTO.setIsbn("19384601239756");
//
//
//        ResponseEntity<BookDTO> createBookResponse
//                = RestFixture.createBook(Long.valueOf(26), bookDTO);
//        BookDTO createdBookDTO = createBookResponse.getBody();
//
//        MatcherAssert.assertThat(createdBookDTO.getBookId(), is(notNullValue()));
//    }
//
//}
