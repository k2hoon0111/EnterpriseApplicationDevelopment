package lv.javaguru.ee.deliveryagency.core.services;

import lv.javaguru.ee.deliveryagency.core.commands.DomainCommand;
import lv.javaguru.ee.deliveryagency.core.commands.DomainCommandResult;

/**
 * Created by Viktor on 27/07/2014.
 */
public interface DomainCommandHandler<C extends DomainCommand, R extends DomainCommandResult> {

    R execute(C command);

    Class getCommandType();

}
