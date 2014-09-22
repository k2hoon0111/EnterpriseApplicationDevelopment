package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.domain.Book;

public class GetBookResult implements DomainCommandResult {

    private Book book;

    public GetBookResult(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}
