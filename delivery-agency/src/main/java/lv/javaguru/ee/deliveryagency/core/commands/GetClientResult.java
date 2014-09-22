package lv.javaguru.ee.deliveryagency.core.commands;

import lv.javaguru.ee.deliveryagency.core.domain.Client;

/**
 * Created by Viktor on 16/09/2014.
 */
public class GetClientResult implements DomainCommandResult {

    private Client client;

    public GetClientResult(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
