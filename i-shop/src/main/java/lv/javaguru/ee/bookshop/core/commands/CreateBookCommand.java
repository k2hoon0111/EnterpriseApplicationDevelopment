package lv.javaguru.ee.bookshop.core.commands;

import java.math.BigDecimal;

public class CreateBookCommand implements DomainCommand<CreateBookCommandResult> {
    private String title;
    private String description;
    private BigDecimal price;
    private Integer year;
    private String author;
    private String Isbn;
    private Long categoryId;

    public CreateBookCommand(
            String title,
            String description,
            BigDecimal price,
            Integer year,
            String author,
            String Isbn,
            Long categoryId
    ) {
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
}
