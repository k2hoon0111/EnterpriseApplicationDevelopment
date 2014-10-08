package lv.javaguru.ee.bookshop.integrations.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by MumboJumbo on 20/09/14.
 */
@XmlRootElement(name = "orderDetailDTO")
public class OrderDetailDTO {

    Long orderDetailId;
    Long orderId;
    Long bookId;
    int quantity;

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
