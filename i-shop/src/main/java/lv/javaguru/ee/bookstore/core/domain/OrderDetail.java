package lv.javaguru.ee.bookstore.core.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 10:18
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class OrderDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private Book book;

    private int quantity = 1;

    public OrderDetail() {
        super();
    }

    public OrderDetail(Book book, int quantity) {
        super();
        this.book = book;
        this.quantity = quantity;
    }

    public Long getId() {
        return this.id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BigDecimal getPrice() {
        if (this.book != null) {
            return this.book.getPrice().multiply(new BigDecimal(this.quantity));
        }
        return BigDecimal.ZERO;
    }

}
