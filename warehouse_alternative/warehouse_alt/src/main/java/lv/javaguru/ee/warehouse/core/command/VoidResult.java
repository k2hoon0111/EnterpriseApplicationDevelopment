package lv.javaguru.ee.warehouse.core.command;

/**
 * Created by Viktor on 31/08/2014.
 */
public class VoidResult implements DomainCommandResult {

    public static final VoidResult INSTANCE = new VoidResult();

    private VoidResult() {

    }

    @Override
    public Object getResult() {
        throw new UnsupportedOperationException("Void type can not return result.");
    }
    
}
