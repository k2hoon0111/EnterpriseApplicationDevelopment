//package lv.javaguru.ee.bookshop.core.services;
//
//import lv.javaguru.ee.bookshop.core.commands.DomainCommand;
//import lv.javaguru.ee.bookshop.core.commands.DomainCommandResult;
//import lv.javaguru.ee.bookshop.core.DomainCommandService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.PostConstruct;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class DomainCommandHandlerExecutorImpl implements DomainCommandHandlerExecutor {
//
//    @Autowired
//    private List<DomainCommandService> services;
//
//    private Map<Class, DomainCommandService> commandServiceMap;
//
//
//    @PostConstruct
//    public void init() {
//        commandServiceMap = new HashMap<>();
//        if(services != null && !services.isEmpty()) {
//            for (DomainCommandService service : services) {
//                Class domainCommandClass = service.getCommandType();
//                commandServiceMap.put(domainCommandClass, service);
//            }
//        }
//    }
//
//    @Transactional("hibernateTX")
//    public <T extends DomainCommandResult> T execute(DomainCommand<T> domainCommand) {
//        DomainCommandService service = commandServiceMap.get(domainCommand.getClass());
//        if(service != null) {
//            return (T)service.execute(domainCommand);
//        } else {
//            throw new IllegalArgumentException("Unknown domain command! " + domainCommand.toString());
//        }
//    }
//
//}
