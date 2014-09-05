package lv.javaguru.ee.bookstore.core;

/**
 * Created by Viktor on 27/07/2014.
 */
public interface DomainCommandService<C extends DomainCommand, R extends DomainCommandResult> {

    R execute(C command);

    Class getCommandType();

}
