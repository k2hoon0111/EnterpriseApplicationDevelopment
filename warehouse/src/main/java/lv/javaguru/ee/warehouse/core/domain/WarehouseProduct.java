package lv.javaguru.ee.warehouse.core.domain;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "WAREHOUSE_PRODUCTS")
@IdClass(value = WarehouseProductPK.class)
public class WarehouseProduct {
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Warehouse warehouse;
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Product product;
    
    private Integer count;
    
    private Integer price;

    public WarehouseProduct() {
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
          
    
    
}
