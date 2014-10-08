package lv.javaguru.ee.deliveryagency.core.commands.client;

import lv.javaguru.ee.deliveryagency.core.commands.DomainCommandResult;
import lv.javaguru.ee.deliveryagency.core.domain.Client;

/**
 * Created by Viktor on 05/10/2014.
 */
public class DeleteClientResult implements DomainCommandResult {

    private Client client;


    public DeleteClientResult(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
