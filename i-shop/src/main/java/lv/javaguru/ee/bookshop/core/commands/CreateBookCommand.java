package lv.javaguru.ee.bookshop.core.commands;

import lv.javaguru.ee.bookshop.core.DomainCommand;
import lv.javaguru.ee.bookshop.core.domain.Category;

import java.math.BigDecimal;

public class CreateBookCommand implements DomainCommand {

    private Long bookId;
    private String title;
    private String description;
    private BigDecimal price;
    private Integer year;
    private String author;
    private String Isbn;
    private Category category;

    public CreateBookCommand(Long bookId,
                             String title,
                             String description,
                             BigDecimal price,
                             Integer year,
                             String author,
                             String Isbn,
                             Category category
    ) {
        this.bookId = bookId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.year = year;
        this.author = author;
        this.Isbn = Isbn;
        this.category = category;
    }

    public Long getBookId() {
        return bookId;
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

    public Category getCategory() {
        return category;
    }
}
