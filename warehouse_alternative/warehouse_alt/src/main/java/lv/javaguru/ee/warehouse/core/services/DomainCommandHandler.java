package lv.javaguru.ee.warehouse.core.services;

import lv.javaguru.ee.warehouse.core.command.DomainCommand;
import lv.javaguru.ee.warehouse.core.command.DomainCommandResult;

/**
 * Created by Viktor on 27/07/2014. 
 */
public interface DomainCommandHandler<C extends DomainCommand<R>, R extends DomainCommandResult> {

    R execute(C command);

    Class<C> getCommandType();

    void validate(C command);
    
}
