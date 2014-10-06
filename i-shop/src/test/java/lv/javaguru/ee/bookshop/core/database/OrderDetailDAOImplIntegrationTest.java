//package lv.javaguru.ee.bookshop.core.database;
//
//import lv.javaguru.ee.bookshop.core.domain.OrderDetail;
//import org.junit.Assert;
//import org.junit.Test;
//
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//
///**
//* Created with IntelliJ IDEA.
//* User: MumboJumbo
//* Date: 10/09/14
//* Time: 19:05
//* To change this template use File | Settings | File Templates.
//*/
//public class OrderDetailDAOImplIntegrationTest extends DatabaseIntegrationTest {
//    @Test
//    public void testCreateOrderDetail() {
//        OrderDetail orderDetail = createDefaultOrderDetail();
//        Assert.assertNotNull(orderDetail);
//        assertThat(orderDetail.getOrderDetailId(), is(nullValue()));
//        saveOrderDetail(orderDetail);
//        assertThat(orderDetail.getOrderDetailId(), is(notNullValue()));
//    }
//}
