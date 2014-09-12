//package lv.javaguru.ee.bookshop.core.database;
//
//import lv.javaguru.ee.bookshop.core.domain.Order;
//import org.junit.Assert;
//import org.junit.Test;
//
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//
///**
//* Created with IntelliJ IDEA.
//* User: MumboJumbo
//* Date: 09/09/14
//* Time: 17:58
//* To change this template use File | Settings | File Templates.
//*/
//public class OrderDAOImplIntegrationTest extends DatabaseIntegrationTest{
//    @Test
//    public void testCreateOrder() {
//        Order order = createDefaultOrder();
//        Assert.assertNotNull(order);
//        assertThat(order.getOrderId(), is(nullValue()));
//        saveOrder(order);
//        assertThat(order.getOrderId(), is(notNullValue()));
//    }
//
//}
