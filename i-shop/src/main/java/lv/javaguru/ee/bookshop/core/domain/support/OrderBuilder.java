package lv.javaguru.ee.bookshop.core.domain.support;

import lv.javaguru.ee.bookshop.core.domain.Account;
import lv.javaguru.ee.bookshop.core.domain.Book;
import lv.javaguru.ee.bookshop.core.domain.Order;
import lv.javaguru.ee.bookshop.core.domain.OrderDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */
@Component
public class OrderBuilder extends EntityBuilder<Order> {

    private List<OrderDetail> orderDetails;

    @Override
    void initProduct() {
        this.product = new Order();
        this.orderDetails = new ArrayList<OrderDetail>();
    }

    public OrderBuilder addBook(Book book, int quantity) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantity(quantity);

        orderDetail.setBook(book);
        this.orderDetails.add(orderDetail);
        return this;
    }

    public OrderBuilder addBooks(Map<Book, Integer> map) {
        for (Entry<Book, Integer> entry : map.entrySet()) {
            addBook(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public OrderBuilder deliveryDate(Date date) {
        this.product.setDeliveryDate(date);
        return this;
    }

    public OrderBuilder orderDate(Date date) {
        this.product.setOrderDate(date);
        return this;
    }

    public OrderBuilder account(Account account) {
        this.product.setAccount(account);
        return this;
    }

    @Override
    Order assembleProduct() {
        for (OrderDetail orderDetail : this.orderDetails) {
            this.product.addOrderDetail(orderDetail);
        }
        return this.product;
    }
}