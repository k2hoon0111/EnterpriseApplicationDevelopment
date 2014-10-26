package lv.javaguru.ee.warehouse.core.domain;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;


/**
 * Created by Yuri D. on 2014.09.08..
 */
@Entity
@Table(name = "PRODUCTS")
@Audited
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;
    
    @Version
    @Column(name = "VERSION", nullable = false)    
    @NotAudited
    private Long version;
    
    @Column(name = "CODE", nullable = false, unique = true)
    private Long code;
    
    @Column(name = "TITLE", length = 50)
    private String title;
    
    @Column(name = "DESCRIPTION", length = 100)
    private String description;
                 
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "product")    
    @NotAudited 
    private List<WarehouseProduct> warehouseProducts = new ArrayList<>();
    
    
    @OneToMany(mappedBy="product", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true)
    @MapKey(name="name")       
    private Map<String, ProductProperties> productProperties;

    public Product() {
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

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
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

    public List<WarehouseProduct> getWarehouseProducts() {
        return warehouseProducts;
    }

    public void setWarehouseProducts(List<WarehouseProduct> warehouseProducts) {
        this.warehouseProducts = warehouseProducts;
    }

    public Map<String, ProductProperties> getProductProperties() {
        return productProperties;
    }

    public void setProductProperties(Map<String, ProductProperties> productProperties) {
        this.productProperties = productProperties;
    }

    public void addWarehouseProduct(WarehouseProduct... warehouseProducts) {        
        if (warehouseProducts != null) {        
            this.warehouseProducts.addAll(asList(warehouseProducts));            
        } 
    }
       
    public void addToWarehouse(Warehouse warehouse, Integer count, Integer price) {    
        WarehouseProduct wp = new WarehouseProduct(warehouse, this);
        wp.setCount(count);
        wp.setPrice(price);
        
        this.addWarehouseProduct(wp);
        warehouse.addWarehouseProduct(wp);        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.version);
        hash = 37 * hash + Objects.hashCode(this.code);
        hash = 37 * hash + Objects.hashCode(this.title);
        hash = 37 * hash + Objects.hashCode(this.description);
        //hash = 37 * hash + Objects.hashCode(this.warehouseProducts);
        //hash = 37 * hash + Objects.hashCode(this.productProperties);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
//        if (!Objects.equals(this.warehouseProducts, other.warehouseProducts)) {
//            return false;
//        }
//        if (!Objects.equals(this.productProperties, other.productProperties)) {
//            return false;
//        }
        return true;
    }
    
    
    
}
