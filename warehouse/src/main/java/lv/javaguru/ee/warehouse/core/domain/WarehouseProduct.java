package lv.javaguru.ee.warehouse.core.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
    @ManyToOne(fetch = FetchType.LAZY, 
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, 
            optional = true)
    @AttributeOverrides({
        @AttributeOverride(name = "warehouse", column = @Column(name = "WAREHOUSE_ID"))
    })
    private Warehouse warehouse;
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY, 
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, 
            optional = true)
    @AttributeOverrides({         
        @AttributeOverride(name = "product", column = @Column(name = "PRODUCT_ID"))
    })
    private Product product;
    
    @Column(name = "COUNT", nullable = false)
    private Integer count;
    
    @Column(name = "PRICE", nullable = false)
    private Integer price;

    public WarehouseProduct() {
    }

    public WarehouseProduct(Warehouse warehouse, Product product) {
        this.warehouse = warehouse;
        this.product = product;       
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
