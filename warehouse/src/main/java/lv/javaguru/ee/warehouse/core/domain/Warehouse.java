package lv.javaguru.ee.warehouse.core.domain;

import java.util.List;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author dell
 */

@Entity
@Table(name = "WAREHOUSES")
public class Warehouse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", unique = true, nullable = false)
    private Long id;
    
    @Version
    @Column(name = "VERSION")
    private Long version;
    
    @Column(name = "TITLE", nullable = false, length = 50)
    private String title;
    
    @Column(name = "DESCRIPTION", length = 100)
    private String description;
    
    //embedded
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country",    column = @Column(name = "COUNTRY", length = 30)),
            @AttributeOverride(name = "city",       column = @Column(name = "CITY", length = 30)),
            @AttributeOverride(name = "postIndex",  column = @Column(name = "POSTINDEX", length = 10)),
            @AttributeOverride(name = "street",     column = @Column(name = "STREET", length = 50)),
            @AttributeOverride(name = "house",      column = @Column(name = "HOUSE", length = 15))
    })
    private WarehouseAddress address;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = {
        CascadeType.DETACH, 
        CascadeType.MERGE, 
        CascadeType.PERSIST, 
        CascadeType.REFRESH}, mappedBy = "warehouse") 
    private List<WarehouseProduct> warehouseProducts;

    public Warehouse() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WarehouseAddress getAddress() {
        return address;
    }

    public void setAddress(WarehouseAddress address) {
        this.address = address;
    }

    public List<WarehouseProduct> getProducts() {
        return warehouseProducts;
    }

    public void setProducts(List<WarehouseProduct> warehouseProducts) {
        this.warehouseProducts = warehouseProducts;
    }
              
}
