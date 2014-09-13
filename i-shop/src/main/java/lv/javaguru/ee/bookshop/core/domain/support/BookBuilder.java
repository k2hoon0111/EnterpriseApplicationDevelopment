//package lv.javaguru.ee.bookshop.core.domain.support;
//
//import lv.javaguru.ee.bookshop.core.domain.Book;
//import lv.javaguru.ee.bookshop.core.domain.Category;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//
///**
// * Created with IntelliJ IDEA.
// * User: MumboJumbo
// * Date: 05/09/14
// * Time: 10:06
// * To change this template use File | Settings | File Templates.
// */
//@Component
//public class BookBuilder extends EntityBuilder<Book> {
//
//    private Book product;
//
//    @Override
//    void initProduct() {
//        product = new Book();
//    }
//
//    public BookBuilder title(String title) {
//        product.setTitle(title);
//        return this;
//    }
//
//    public BookBuilder description(String description) {
//        product.setDescription(description);
//        return this;
//    }
//
//    public BookBuilder author(String author) {
//        product.setAuthor(author);
//        return this;
//    }
//
//    public BookBuilder year(int year) {
//        product.setYear(year);
//        return this;
//    }
//
//    public BookBuilder price(String price) {
//        product.setPrice(new BigDecimal(price));
//        return this;
//    }
//
//    public BookBuilder category(Category category) {
//        product.setCategory(category);
//        return this;
//    }
//
//    public BookBuilder isbn(String isbn) {
//        product.setIsbn(isbn);
//        return this;
//    }
//
//    @Override
//    Book assembleProduct() {
//        return product;
//    }
//}