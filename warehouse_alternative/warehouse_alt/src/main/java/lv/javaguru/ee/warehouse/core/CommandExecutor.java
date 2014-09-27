package lv.javaguru.ee.warehouse.core;

import lv.javaguru.ee.warehouse.core.command.DomainCommand;
import lv.javaguru.ee.warehouse.core.command.DomainCommandResult;

/**
 * Created by Viktor on 08/09/2014.
 */
public interface CommandExecutor {

    <T extends DomainCommandResult> T execute(DomainCommand<T> domainCommand);

}
