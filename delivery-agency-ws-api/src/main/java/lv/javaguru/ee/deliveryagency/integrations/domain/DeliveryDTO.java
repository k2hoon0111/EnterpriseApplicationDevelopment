package lv.javaguru.ee.deliveryagency.integrations.domain;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "deliveryDTO")
public class DeliveryDTO {
	
	private Long deliveryId;

	
	public Long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}

}
