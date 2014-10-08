package lv.javaguru.ee.deliveryagency.core.commands.deliveryaddress;

import lv.javaguru.ee.deliveryagency.core.commands.DomainCommandResult;
import lv.javaguru.ee.deliveryagency.core.domain.DeliveryAddress;

/**
 * Created by Viktor on 27/07/2014.
 */
public class CreateDeliveryAddressResult implements DomainCommandResult {

	private DeliveryAddress deliveryAddress;

	public CreateDeliveryAddressResult(DeliveryAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public DeliveryAddress getDeliveryAddress() {
		return deliveryAddress;
	}
}
