package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.domain.Book;

public class CreateBookResult implements DomainCommandResult {

    private Book book;

    public CreateBookResult(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

}
