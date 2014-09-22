package lv.javaguru.ee.warehouse.core.database.hibernate;

//import static lv.javaguru.ee.warehouse.core.domain.OrderBuilder.*;
//import static lv.javaguru.ee.warehouse.core.domain.ProductBuilder.*;

import lv.javaguru.ee.warehouse.config.CoreConfig;

import lv.javaguru.ee.warehouse.core.domain.Order;
import lv.javaguru.ee.warehouse.core.database.OrderDAO;
import lv.javaguru.ee.warehouse.core.domain.OrderBuilder;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


/**
 * Created by Yuri D. on 2014.09.23..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CoreConfig.class)
@TransactionConfiguration(transactionManager = "hibernateTX", defaultRollback = false)
public abstract class DatabaseHibernateTest {

    @Autowired
    @Qualifier("hibernateTX")
    protected PlatformTransactionManager transactionManager;

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    protected OrderDAO orderDAO;

//    @Autowired
//    protected ProductDAO productDAO;

    protected Order getDefaultOrder() {
        return OrderBuilder.createOrder()
                .withClientId(new Long(1))
                .withProductId("AA-11")
                .withItemsQuantity(5).build();
    }

    protected Order createDefaultOrder() {
        Order order = getDefaultOrder();
        orderDAO.create(order);
        return order;
    }


}
