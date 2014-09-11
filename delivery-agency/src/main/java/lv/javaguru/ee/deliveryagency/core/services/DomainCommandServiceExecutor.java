package lv.javaguru.ee.deliveryagency.core.services;

import lv.javaguru.ee.deliveryagency.core.DomainCommand;
import lv.javaguru.ee.deliveryagency.core.DomainCommandResult;

/**
 * Created by Viktor on 08/09/2014.
 */
public interface DomainCommandServiceExecutor {

    <T extends DomainCommandResult> T execute(DomainCommand<T> domainCommand);

}
