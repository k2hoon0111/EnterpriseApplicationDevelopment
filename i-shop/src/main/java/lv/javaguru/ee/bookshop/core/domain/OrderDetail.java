package lv.javaguru.ee.bookshop.core.domain;

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
@Table(name = "orderDetails")
public class OrderDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderDetailId")
    private Long orderDetailId;

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bookId")
    private Book book;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private Order order;

    private int quantity = 1;

    public OrderDetail() {
        super();
    }

    public OrderDetail(Book book, int quantity) {
        super();
        this.book = book;
        this.quantity = quantity;
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
