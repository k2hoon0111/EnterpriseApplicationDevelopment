package lv.javaguru.ee.bookshop.core.commands;

public class GetBookCommand implements DomainCommand<GetBookResult> {

    private Long bookId;
    private Long categoryId;

    public GetBookCommand(Long categoryId, Long bookId) {
        this.bookId = bookId;
        this.categoryId = categoryId;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
