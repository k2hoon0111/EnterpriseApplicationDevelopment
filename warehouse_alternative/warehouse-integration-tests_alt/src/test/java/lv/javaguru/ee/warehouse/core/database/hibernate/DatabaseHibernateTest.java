package lv.javaguru.ee.warehouse.core.database.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import lv.javaguru.ee.warehouse.config.CoreConfig;
import lv.javaguru.ee.warehouse.core.database.OrderDAO;
import lv.javaguru.ee.warehouse.core.database.ProductDAO;
import lv.javaguru.ee.warehouse.core.database.ProductPropertiesDAO;
import lv.javaguru.ee.warehouse.core.database.WarehouseDAO;
import lv.javaguru.ee.warehouse.core.domain.Order;
import static lv.javaguru.ee.warehouse.core.domain.Order.Direction.INCOMING;
import lv.javaguru.ee.warehouse.core.domain.Product;
import lv.javaguru.ee.warehouse.core.domain.ProductProperties;
import lv.javaguru.ee.warehouse.core.domain.Warehouse;
import lv.javaguru.ee.warehouse.core.domain.WarehouseAddress;
import lv.javaguru.ee.warehouse.core.domain.WarehouseProduct;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CoreConfig.class)
@ActiveProfiles("test")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public abstract class DatabaseHibernateTest {

    @Autowired    
    protected PlatformTransactionManager transactionManager;

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    protected OrderDAO orderDAO;

    @Autowired
    protected ProductDAO productDAO;

    @Autowired
    protected ProductPropertiesDAO productPropertiesDAO;

    @Autowired
    protected WarehouseDAO warehouseDAO;

    @Before
    public void cleanDatabase() {
        List<String> tableNames = getTableNames();
        
        for (final String tableName : tableNames) {
            doInTransaction(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                    Session session = sessionFactory.getCurrentSession();
                    String queryString = "DELETE FROM " + tableName;
                    Query query = session.createSQLQuery(queryString);
                    query.executeUpdate();
                }
            });
        } 
    }

    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        
        // audit tables
        tableNames.add("PRODUCT_PROPERTIES_AUD");
        tableNames.add("PRODUCTS_AUD");
        tableNames.add("REVINFO");
                
        // real tables
        tableNames.add("ORDER_PRODUCTS");
        tableNames.add("ORDERS");        
        tableNames.add("PRODUCT_PROPERTIES");
        tableNames.add("WAREHOUSE_PRODUCTS");
        tableNames.add("PRODUCTS");
        tableNames.add("WAREHOUSES");
        
        return tableNames;
    }

    protected Product getDefaultProduct() {
        Product product = new Product();
        product.setCode(1111111111111L);
        return product;
    }
    
    protected Product createDefaultProduct() {
        Product product = getDefaultProduct();
        productDAO.create(product);
        return product;
    }
    
    protected Warehouse getDefaultWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setTitle("warehouseTitle");
        return warehouse;
    }
    
    protected Warehouse createDefaultWarehouse() {
        Warehouse warehouse = getDefaultWarehouse();
        warehouseDAO.create(warehouse);
        return warehouse;
    }
    
     protected Order getDefaultOrder(Warehouse warehouse, Product product) {
        Order order = new Order();    
        order.setDirection(INCOMING);
        order.setWarehouse(warehouse);
        order.addProduct(product);
        order.setAmount(10);
        order.setQuantity(1);
        return order;
    }
    
    protected Order createDefaultOrder(Warehouse warehouse, Product product) {
        Order order = getDefaultOrder(warehouse, product);
        orderDAO.create(order);
        return order;
    }
    
    protected ProductProperties getDefaultProductProperties(Product product) {
        ProductProperties pp = new ProductProperties();
        pp.setProduct(product);
        pp.setName("default");
        pp.setValue("default value");
        return pp;
    }
    
    protected ProductProperties createDefaultProductProperties(Product product) {
        ProductProperties pp = getDefaultProductProperties(product);
        productPropertiesDAO.create(pp);
        return pp;
    }
    
    
    protected WarehouseProduct getDefaultWarehouseProduct(Warehouse warehouse, Product product) {        
        //product.addToWarehouse(warehouse, 1, 10);
        WarehouseProduct wp = new WarehouseProduct(warehouse, product);               
        wp.setCount(1);
        wp.setPrice(10);
        warehouse.addWarehouseProduct(wp);
        product.addWarehouseProduct(wp);
        return wp;
    }
    
    protected WarehouseProduct createWarehouseProduct(Warehouse warehouse, Product product) {
        WarehouseProduct wp = getDefaultWarehouseProduct(warehouse, product);  
        //warehouse.addWarehouseProduct(wp);  
        warehouseDAO.update(warehouse);
        return wp;
    }
    

    protected WarehouseAddress getDefaultWarehouseAddress() {        
        WarehouseAddress wa = new WarehouseAddress();
        wa.setCountry("Latvija");
        wa.setStreet("Kalku iela");
        wa.setPostIndex("LV 4566");
        wa.setHouse("34/6");               
        return wa;
    }
    
    protected void doInTransaction(TransactionCallbackWithoutResult callbackWithoutResult) {
        TransactionTemplate tt = new TransactionTemplate(transactionManager);
        tt.execute(callbackWithoutResult);
    }

}
