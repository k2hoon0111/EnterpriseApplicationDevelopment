package lv.javaguru.ee.deliveryagency.core.services;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lv.javaguru.ee.deliveryagency.core.commands.CreateDeliveryCommand;
import lv.javaguru.ee.deliveryagency.core.commands.CreateDeliveryCommandResult;
import lv.javaguru.ee.deliveryagency.core.database.hibernate.DatabaseHibernateTest;

/**
 * Created by Viktor on 08/09/2014.
 */
public class CreateDeliveryCommandHandlerTest extends DatabaseHibernateTest {

    @Autowired
    private DomainCommandHandlerExecutor serviceExecutor;

    @Test
    public void testCommand() {
        CreateDeliveryCommand command = new CreateDeliveryCommand();
        CreateDeliveryCommandResult commandResult = serviceExecutor.execute(command);
        assertThat(commandResult, is(notNullValue()));
        assertThat(commandResult.getDelivery(), is(notNullValue()));
        assertThat(commandResult.getDelivery().getDeliveryId(), is(notNullValue()));
    }

}
