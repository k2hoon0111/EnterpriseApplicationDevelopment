package lv.javaguru.ee.deliveryagency.integrations.domain.builders;

import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;

public class DeliveryDTOBuilder {

	private Long deliveryId;
	
	public static DeliveryDTOBuilder createDeliveryDTO() {
		return new DeliveryDTOBuilder();
	}

	private DeliveryDTOBuilder() { }
	
	public DeliveryDTO build() {
		DeliveryDTO dto = new DeliveryDTO();
		dto.setDeliveryId(deliveryId);
		return dto;
	}
	
	public DeliveryDTOBuilder withDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
		return this;
	} 
	
}
