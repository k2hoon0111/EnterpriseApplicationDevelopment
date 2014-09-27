package lv.javaguru.ee.warehouse.core.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author dell
 */
public class WarehouseProductPK implements Serializable {
    
    private Warehouse warehouse;
        
    private Product product;

    public WarehouseProductPK() {
    }

    public WarehouseProductPK(Warehouse warehouse, Product product) {
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
        
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.warehouse);
        hash = 83 * hash + Objects.hashCode(this.product);
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
        final WarehouseProductPK other = (WarehouseProductPK) obj;
        if (!Objects.equals(this.warehouse, other.warehouse)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }
       
}
