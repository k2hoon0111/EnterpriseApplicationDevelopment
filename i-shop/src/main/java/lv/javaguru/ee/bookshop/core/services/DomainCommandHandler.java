package lv.javaguru.ee.bookshop.core.services;

import lv.javaguru.ee.bookshop.core.commands.DomainCommand;
import lv.javaguru.ee.bookshop.core.commands.DomainCommandResult;

/**
 * Created by Viktor on 27/07/2014.
 */
public interface DomainCommandHandler<C extends DomainCommand, R extends DomainCommandResult> {

    R execute(C command);

    Class getCommandType();

}
