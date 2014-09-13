package lv.javaguru.ee.deliveryagency.core.services;

import lv.javaguru.ee.deliveryagency.core.DomainCommandService;
import lv.javaguru.ee.deliveryagency.core.commands.CreateDeliveryAddressCommand;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class DomainCommandHandlerExecutorImplTest {

    private DomainCommandHandlerExecutorImpl serviceExecutor;


    @Before
    public void init() {
        List<DomainCommandService> services = new ArrayList<>();
        initExecutorService(services);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForUnknownDomainCommand() {
        CreateDeliveryAddressCommand command = new CreateDeliveryAddressCommand(null, null, null, null, null);
        serviceExecutor.execute(command);
    }

    @Test
    public void executeKnownCommand() {
        DomainCommandService commandService = mock(DomainCommandService.class);
        doReturn(CreateDeliveryAddressCommand.class).when(commandService).getCommandType();
        List<DomainCommandService> services = new ArrayList<>();
        services.add(commandService);

        initExecutorService(services);

        CreateDeliveryAddressCommand command = new CreateDeliveryAddressCommand(null, null, null, null, null);
        serviceExecutor.execute(command);

        verify(commandService, times(1)).execute(command);
    }

    private void initExecutorService(List<DomainCommandService> services) {
        serviceExecutor = new DomainCommandHandlerExecutorImpl();
        ReflectionTestUtils.setField(serviceExecutor, "services", services);
        serviceExecutor.init();
    }

}