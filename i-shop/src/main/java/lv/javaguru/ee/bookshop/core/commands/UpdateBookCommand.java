package lv.javaguru.ee.bookshop.core.commands;

import java.math.BigDecimal;

/**
 * Created by nikoboro on 2014.09.18..
 */

public class UpdateBookCommand implements DomainCommand<UpdateBookResult> {
    private Long bookId;
    private String title;
    private String description;
    private BigDecimal price;
    private Integer year;
    private String author;
    private String Isbn;
    private Long categoryId;

    public UpdateBookCommand(
            Long bookId,
            String title,
            String description,
            BigDecimal price,
            Integer year,
            String author,
            String Isbn,
            Long categoryId
    ) {
        this.bookId = bookId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.year = year;
        this.author = author;
        this.Isbn = Isbn;
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return Isbn;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
