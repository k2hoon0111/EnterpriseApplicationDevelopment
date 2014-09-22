//package lv.javaguru.ee.bookshop.core.services;
//
//import lv.javaguru.ee.bookshop.core.DomainCommandService;
//import lv.javaguru.ee.bookshop.core.commands.CreateBookCommand;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//
//public class DomainCommandHandlerExecutorImplTest {
//
//    private DomainCommandHandlerExecutorImpl serviceExecutor;
//
//    @Before
//    public void init() {
//        List<DomainCommandService> services = new ArrayList<>();
//        initExecutorService(services);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void shouldThrowExceptionForUnknownDomainCommand() {
//        CreateBookCommand command = new CreateBookCommand(null, null, null, null, null, null, null);
//        serviceExecutor.execute(command);
//    }
//
//    @Test
//    public void executeKnownCommand() {
//        DomainCommandService commandService = mock(DomainCommandService.class);
//        doReturn(CreateBookCommand.class).when(commandService).getCommandType();
//        List<DomainCommandService> services = new ArrayList<>();
//        services.add(commandService);
//
//        initExecutorService(services);
//
//        CreateBookCommand command = new CreateBookCommand(null, null, null, null, null, null, null);
//        serviceExecutor.execute(command);
//
//        verify(commandService, times(1)).execute(command);
//    }
//
//    private void initExecutorService(List<DomainCommandService> services) {
//        serviceExecutor = new DomainCommandHandlerExecutorImpl();
//        ReflectionTestUtils.setField(serviceExecutor, "services", services);
//        serviceExecutor.init();
//    }
//
//}