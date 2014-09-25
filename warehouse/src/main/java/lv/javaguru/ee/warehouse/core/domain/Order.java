package lv.javaguru.ee.warehouse.core.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Created by Yuri D. on 2014.09.08..
 */
@Entity
@Table(name = "ORDERS")
public class Order {
           
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED", nullable = false)
    private Date created = new Date();
    
    @Enumerated(EnumType.STRING)
    @Column(name = "DIRECTION", length = 10)
    private Direction direction;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="PRODUCT_ID", nullable = false)    
    private Product product;
    
    @ManyToOne(fetch = FetchType.EAGER)    
    @JoinColumn(name="WAREHOUSE_ID", nullable = false)
    private Warehouse warehouse;
    
    @Column(name = "QUANTITY")
    private Integer quantity;
    
    @Column(name = "AMOUNT")    
    private Integer amount;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getCreated() {
        return created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
            
    public static enum Direction {
        INCOMING, OUTGOING    
    }
    
}
