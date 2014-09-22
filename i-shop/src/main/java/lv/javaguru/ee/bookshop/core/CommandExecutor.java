package lv.javaguru.ee.bookshop.core;

import lv.javaguru.ee.bookshop.core.commands.DomainCommand;
import lv.javaguru.ee.bookshop.core.commands.DomainCommandResult;

/**
 * Created by Viktor on 08/09/2014.
 */
public interface CommandExecutor {

    <T extends DomainCommandResult> T execute(DomainCommand<T> domainCommand);

}
