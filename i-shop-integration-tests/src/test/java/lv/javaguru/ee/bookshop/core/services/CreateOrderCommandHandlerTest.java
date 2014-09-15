//package lv.javaguru.ee.bookshop.core.services;
//
//import lv.javaguru.ee.bookshop.core.commands.CreateOrderCommand;
//import lv.javaguru.ee.bookshop.core.commands.CreateOrderCommandResult;
//import lv.javaguru.ee.bookshop.core.database.hibernate.DatabaseHibernateTest;
//import lv.javaguru.ee.bookshop.core.domain.Order;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.hamcrest.MatcherAssert.assertThat;
//
///**
// * Created by MumboJumbo on 15/09/14.
// */
//
//public class CreateOrderCommandHandlerTest extends DatabaseHibernateTest {
//
//    @Autowired
//    private DomainCommandHandlerExecutor serviceExecutor;
//
//    @Test
//    public void testCommand() {
//        Order order = getDefaultOrder();
//        CreateOrderCommand command = new CreateOrderCommand(
//                order.getShippingAddress(),
//                order.getBillingAddress(),
//                order.getAccount(),
//                order.isBillingSameAsShipping(),
//                order.getOrderDate(),
//                order.getDeliveryDate(),
//                order.getTotalOrderPrice()
//        );
//        CreateOrderCommandResult commandResult = serviceExecutor.execute(command);
//        assertThat(commandResult, is(notNullValue()));
//        assertThat(commandResult.getOrder(), is(notNullValue()));
//        assertThat(commandResult.getOrder().getOrderId(), is(notNullValue()));
//    }
//
//}
