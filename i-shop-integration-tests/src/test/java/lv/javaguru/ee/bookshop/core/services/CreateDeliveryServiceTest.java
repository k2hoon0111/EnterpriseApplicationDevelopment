//package lv.javaguru.ee.bookshop.core.services;
//
//import lv.javaguru.ee.bookshop.core.commands.CreateDeliveryCommand;
//import lv.javaguru.ee.bookshop.core.commands.CreateDeliveryCommandResult;
//import lv.javaguru.ee.bookshop.core.database.hibernate.DatabaseHibernateTest;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.hamcrest.MatcherAssert.assertThat;
//
///**
// * Created by Viktor on 08/09/2014.
// */
//public class CreateDeliveryServiceTest extends DatabaseHibernateTest {
//
//    @Autowired
//    private DomainCommandServiceExecutor serviceExecutor;
//
//    @Test
//    public void testCommand() {
//        CreateDeliveryCommand command = new CreateDeliveryCommand();
//        CreateDeliveryCommandResult commandResult = serviceExecutor.execute(command);
//        assertThat(commandResult, is(notNullValue()));
//        assertThat(commandResult.getDelivery(), is(notNullValue()));
//        assertThat(commandResult.getDelivery().getDeliveryId(), is(notNullValue()));
//    }
//
//}
