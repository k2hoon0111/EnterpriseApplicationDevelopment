package lv.javaguru.ee.deliveryagency.core;

import lv.javaguru.ee.deliveryagency.core.commands.DomainCommand;
import lv.javaguru.ee.deliveryagency.core.commands.DomainCommandResult;

/**
 * Created by Viktor on 08/09/2014.
 */
public interface CommandExecutor {

    <T extends DomainCommandResult> T execute(DomainCommand<T> domainCommand);

}
