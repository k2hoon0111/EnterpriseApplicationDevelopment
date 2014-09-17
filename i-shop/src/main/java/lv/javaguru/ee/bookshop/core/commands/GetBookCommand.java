package lv.javaguru.ee.bookshop.core.commands;

public class GetBookCommand implements DomainCommand<GetBookResult> {

    private Long bookId;

    public GetBookCommand(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }


}
