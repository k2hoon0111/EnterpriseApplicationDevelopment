package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.DomainCommandResult;
import lv.javaguru.ee.bookshop.core.domain.Book;

public class CreateBookCommandResult implements DomainCommandResult {

    private Book book;

    public CreateBookCommandResult(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

}
