package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.domain.Book;

/**
 * Created by nikoboro on 2014.09.18..
 */

public class DeleteBookResult implements DomainCommandResult {

    private Book book;

    public DeleteBookResult(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

}