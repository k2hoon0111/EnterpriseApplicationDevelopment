package lv.javaguru.ee.warehouse.core;

import java.util.ArrayList;
import java.util.List;
import lv.javaguru.ee.warehouse.core.command.ProductCRUDCommand;
import lv.javaguru.ee.warehouse.core.services.DomainCommandHandler;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author dell
 */
public class CommandExecutorImplTest {
        
    private CommandExecutorImpl serviceExecutor;


    @Before
    public void init() {
        List<DomainCommandHandler> services = new ArrayList<>();
        initExecutorService(services);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForUnknownDomainCommand() {
        ProductCRUDCommand command = new ProductCRUDCommand();        
        serviceExecutor.execute(command);
    }

    @Test
    public void executeKnownCommand() {
        DomainCommandHandler commandService = mock(DomainCommandHandler.class);
        doReturn(ProductCRUDCommand.class).when(commandService).getCommandType();
        
        List<DomainCommandHandler> services = new ArrayList<>();
        services.add(commandService);

        initExecutorService(services);

        ProductCRUDCommand command = new ProductCRUDCommand();
        serviceExecutor.execute(command);

        verify(commandService, times(1)).execute(command);
    }

    private void initExecutorService(List<DomainCommandHandler> services) {
        serviceExecutor = new CommandExecutorImpl();
        ReflectionTestUtils.setField(serviceExecutor, "services", services);
        serviceExecutor.init();
    }
            
}
