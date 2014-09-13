//package lv.javaguru.ee.deliveryagency.core.domain;
//
//public class DeliveryAddressBuilder {
//
//	private Long deliveryAddressId;
//	private Delivery delivery;
//	private String city;
//	private String postIndex;
//	private String street;
//	private String house;
//	private String flat;
//
//	private DeliveryAddressBuilder() { }
//
//	public static DeliveryAddressBuilder createDeliveryAddress() {
//		return new DeliveryAddressBuilder();
//	}
//
//	public DeliveryAddress build() {
//		DeliveryAddress deliveryAddress = new DeliveryAddress();
//		deliveryAddress.setDeliveryAddressId(deliveryAddressId);
//		deliveryAddress.setDelivery(delivery);
//		deliveryAddress.setCity(city);
//		deliveryAddress.setPostIndex(postIndex);
//		deliveryAddress.setStreet(street);
//		deliveryAddress.setHouse(house);
//		deliveryAddress.setFlat(flat);
//		return deliveryAddress;
//	}
//
//	public DeliveryAddressBuilder withDeliveryAddressId(Long deliveryAddressId) {
//		this.deliveryAddressId = deliveryAddressId;
//		return this;
//	}
//
//	public DeliveryAddressBuilder with(Delivery delivery) {
//		this.delivery = delivery;
//		return this;
//	}
//
//	public DeliveryAddressBuilder withPostIndex(String postIndex) {
//		this.postIndex = postIndex;
//		return this;
//	}
//
//	public DeliveryAddressBuilder withCity(String city) {
//		this.city = city;
//		return this;
//	}
//
//	public DeliveryAddressBuilder withStreet(String street) {
//		this.street = street;
//		return this;
//	}
//
//	public DeliveryAddressBuilder withHouse(String house) {
//		this.house = house;
//		return this;
//	}
//
//	public DeliveryAddressBuilder withFlat(String flat) {
//		this.flat = flat;
//		return this;
//	}
//
//}
