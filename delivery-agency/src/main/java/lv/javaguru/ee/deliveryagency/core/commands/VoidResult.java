package lv.javaguru.ee.deliveryagency.core.commands;

/**
 * Created by Viktor on 31/08/2014.
 */
public class VoidResult implements DomainCommandResult {

    public static final VoidResult INSTANCE = new VoidResult();

    private VoidResult() {

    }

}
