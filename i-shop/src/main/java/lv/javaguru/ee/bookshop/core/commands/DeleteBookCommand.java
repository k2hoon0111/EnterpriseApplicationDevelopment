package lv.javaguru.ee.bookshop.core.commands;

/**
 * Created by nikoboro on 2014.09.18..
 */

public class DeleteBookCommand implements DomainCommand<DeleteBookResult> {
    private Long bookId;

    public DeleteBookCommand(
            Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
