package lv.javaguru.ee.warehouse.core.domain;

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
import org.hibernate.envers.RelationTargetAuditMode;

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
    private Integer code;
    
    @Column(name = "TITLE", length = 50)
    private String title;
    
    @Column(name = "DESCRIPTION", length = 100)
    private String description;
                 
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "product")
    //@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @NotAudited 
    private List<WarehouseProduct> warehouseProducts;
    
    
    @OneToMany(mappedBy="product", fetch = FetchType.EAGER)
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
                    
}
