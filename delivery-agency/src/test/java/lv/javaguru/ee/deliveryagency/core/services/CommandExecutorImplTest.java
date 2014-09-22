package lv.javaguru.ee.deliveryagency.core.services;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import lv.javaguru.ee.deliveryagency.core.CommandExecutorImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import lv.javaguru.ee.deliveryagency.core.commands.CreateDeliveryAddressCommand;


public class CommandExecutorImplTest {

    private CommandExecutorImpl serviceExecutor;


    @Before
    public void init() {
        List<DomainCommandHandler> services = new ArrayList<>();
        initExecutorService(services);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForUnknownDomainCommand() {
        CreateDeliveryAddressCommand command = new CreateDeliveryAddressCommand(null, null, null, null, null, null);
        serviceExecutor.execute(command);
    }

    @Test
    public void executeKnownCommand() {
        DomainCommandHandler commandService = mock(DomainCommandHandler.class);
        doReturn(CreateDeliveryAddressCommand.class).when(commandService).getCommandType();
        List<DomainCommandHandler> services = new ArrayList<>();
        services.add(commandService);

        initExecutorService(services);

        CreateDeliveryAddressCommand command = new CreateDeliveryAddressCommand(null, null, null, null, null, null);
        serviceExecutor.execute(command);

        verify(commandService, times(1)).execute(command);
    }

    private void initExecutorService(List<DomainCommandHandler> services) {
        serviceExecutor = new CommandExecutorImpl();
        ReflectionTestUtils.setField(serviceExecutor, "services", services);
        serviceExecutor.init();
    }

}