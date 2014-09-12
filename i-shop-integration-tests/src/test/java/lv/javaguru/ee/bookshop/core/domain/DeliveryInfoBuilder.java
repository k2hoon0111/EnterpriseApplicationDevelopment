//package lv.javaguru.ee.deliveryagency.core.domain;
//
//import java.util.Date;
//
//public class DeliveryInfoBuilder {
//
//	private Long deliveryInfoId;
//	private Delivery delivery;
//	private Date desiredDeliveryDate;
//	private String desiredDeliveryTimeFrom;
//	private String desiredDeliveryTimeTo;
//	private String deliveryNotes;
//
//	private DeliveryInfoBuilder() {	}
//
//	public static DeliveryInfoBuilder createDeliveryInfo() {
//		return new DeliveryInfoBuilder();
//	}
//
//	public DeliveryInfo build() {
//		DeliveryInfo deliveryInfo = new DeliveryInfo();
//		deliveryInfo.setDeliveryInfoId(deliveryInfoId);
//		deliveryInfo.setDelivery(delivery);
//		deliveryInfo.setDesiredDeliveryDate(desiredDeliveryDate);
//		deliveryInfo.setDesiredDeliveryTimeFrom(desiredDeliveryTimeFrom);
//		deliveryInfo.setDesiredDeliveryTimeTo(desiredDeliveryTimeTo);
//		deliveryInfo.setDeliveryNotes(deliveryNotes);
//		return deliveryInfo;
//	}
//
//	public DeliveryInfoBuilder withDeliveryInfoId(Long deliveryInfoId) {
//		this.deliveryInfoId = deliveryInfoId;
//		return this;
//	}
//
//	public DeliveryInfoBuilder with(Delivery delivery) {
//		this.delivery = delivery;
//		return this;
//	}
//
//	public DeliveryInfoBuilder withDesiredDeliveryDate(Date desiredDeliveryDate) {
//		this.desiredDeliveryDate = desiredDeliveryDate;
//		return this;
//	}
//
//	public DeliveryInfoBuilder withDesiredDeliveryTimeFrom(String desiredDeliveryTimeFrom) {
//		this.desiredDeliveryTimeFrom = desiredDeliveryTimeFrom;
//		return this;
//	}
//
//	public DeliveryInfoBuilder withDesiredDeliveryTimeTo(String desiredDeliveryTimeTo) {
//		this.desiredDeliveryTimeTo = desiredDeliveryTimeTo;
//		return this;
//	}
//
//	public DeliveryInfoBuilder withDeliveryNotes(String deliveryNotes) {
//		this.deliveryNotes = deliveryNotes;
//		return this;
//	}
//
//}
