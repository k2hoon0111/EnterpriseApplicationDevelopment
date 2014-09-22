package lv.javaguru.ee.warehouse.core.domain;

import javax.persistence.*;

/**
 * Created by Yuri D. on 2014.09.08..
 */


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "productId")
    private String productId;         // isbn
    @Column(name = "clientId")
    private Long clientId;           // product ordered for user
    @Column(name = "itemsQuantity")
    private int itemsQuantity;      // how many items to order

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public int getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(int itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }
}
