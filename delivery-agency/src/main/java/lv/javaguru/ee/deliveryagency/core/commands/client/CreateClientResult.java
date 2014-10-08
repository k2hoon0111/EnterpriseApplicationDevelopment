package lv.javaguru.ee.deliveryagency.core.commands.client;

import lv.javaguru.ee.deliveryagency.core.commands.DomainCommandResult;
import lv.javaguru.ee.deliveryagency.core.domain.Client;

public class CreateClientResult implements DomainCommandResult {

	private Client client;

	public CreateClientResult(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}
}
