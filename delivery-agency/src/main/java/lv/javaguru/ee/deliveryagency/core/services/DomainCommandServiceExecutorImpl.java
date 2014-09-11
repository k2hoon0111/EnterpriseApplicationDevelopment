package lv.javaguru.ee.deliveryagency.core.services;

import lv.javaguru.ee.deliveryagency.core.DomainCommand;
import lv.javaguru.ee.deliveryagency.core.DomainCommandResult;
import lv.javaguru.ee.deliveryagency.core.DomainCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Viktor on 27/07/2014.
 */
@Component
public class DomainCommandServiceExecutorImpl implements DomainCommandServiceExecutor {

    @Autowired
    private List<DomainCommandService> services;

    private Map<Class, DomainCommandService> commandServiceMap;


    @PostConstruct
    public void init() {
        commandServiceMap = new HashMap<>();
        if(services != null && !services.isEmpty()) {
            for (DomainCommandService service : services) {
                Class domainCommandClass = service.getCommandType();
                commandServiceMap.put(domainCommandClass, service);
            }
        }
    }

    @Transactional("hibernateTX")
    public <T extends DomainCommandResult> T execute(DomainCommand<T> domainCommand) {
        DomainCommandService service = commandServiceMap.get(domainCommand.getClass());
        if(service != null) {
            return (T)service.execute(domainCommand);
        } else {
            throw new IllegalArgumentException("Unknown domain command! " + domainCommand.toString());
        }
    }

}
