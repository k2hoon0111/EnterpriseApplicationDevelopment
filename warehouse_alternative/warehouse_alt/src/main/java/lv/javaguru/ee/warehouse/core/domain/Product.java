package lv.javaguru.ee.warehouse.core.domain;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Map;
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
    //@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
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
    
}
