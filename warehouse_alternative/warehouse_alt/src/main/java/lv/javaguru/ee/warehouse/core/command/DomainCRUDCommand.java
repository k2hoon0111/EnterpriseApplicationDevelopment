package lv.javaguru.ee.warehouse.core.command;

/**
 *
 * @author dell
 */
public interface DomainCRUDCommand<T extends DomainCommandResult> extends DomainCommand<T> {
    
    Action getAction();
    void setAction(Action action);
    
}
