package lv.javaguru.ee.warehouse.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lv.javaguru.ee.warehouse.core.command.DomainCommand;
import lv.javaguru.ee.warehouse.core.command.DomainCommandResult;
import lv.javaguru.ee.warehouse.core.services.DomainCommandHandler;

/**
 * Created by Viktor on 27/07/2014.
 */
@Component
public class CommandExecutorImpl implements CommandExecutor {

    @Autowired
    private List<DomainCommandHandler> services;

    private Map<Class, DomainCommandHandler> commandServiceMap;


    @PostConstruct
    public void init() {
        commandServiceMap = new HashMap<>();
        if(services != null && !services.isEmpty()) {
            for (DomainCommandHandler service : services) {
                Class domainCommandClass = service.getCommandType();
                commandServiceMap.put(domainCommandClass, service);
            }
        }
    }

    @Transactional
    @Override
    public <T extends DomainCommandResult> T execute(DomainCommand<T> domainCommand) {
        DomainCommandHandler service = commandServiceMap.get(domainCommand.getClass());
        if(service != null) {
            return (T)service.execute(domainCommand);
        } else {
            throw new IllegalArgumentException("Unknown domain command! " + domainCommand.toString());
        }
    }

}
