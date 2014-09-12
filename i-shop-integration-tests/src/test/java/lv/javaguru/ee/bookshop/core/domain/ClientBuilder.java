//package lv.javaguru.ee.deliveryagency.core.domain;
//
//public class ClientBuilder {
//
//	private Long clientId;
//	private Delivery delivery;
//	private String firstName;
//	private String lastName;
//	private String email;
//	private String phone;
//	private String specialNotes;
//
//	private ClientBuilder() { }
//
//	public static ClientBuilder createClient() {
//		return new ClientBuilder();
//	}
//
//	public Client build() {
//		Client client = new Client();
//		client.setClientId(clientId);
//		client.setDelivery(delivery);
//		client.setFirstName(firstName);
//		client.setLastName(lastName);
//		client.setEmail(email);
//		client.setPhone(phone);
//		client.setSpecialNotes(specialNotes);
//		return client;
//	}
//
//	public ClientBuilder withClientId(Long clientId) {
//		this.clientId = clientId;
//		return this;
//	}
//
//	public ClientBuilder with(Delivery delivery) {
//		this.delivery = delivery;
//		return this;
//	}
//
//	public ClientBuilder withFirstName(String firstName) {
//		this.firstName = firstName;
//		return this;
//	}
//
//	public ClientBuilder withLastName(String lastName) {
//		this.lastName = lastName;
//		return this;
//	}
//
//	public ClientBuilder withEmail(String email) {
//		this.email = email;
//		return this;
//	}
//
//	public ClientBuilder withPhone(String phone) {
//		this.phone = phone;
//		return this;
//	}
//
//	public ClientBuilder withSpecialNotes(String specialNotes) {
//		this.specialNotes = specialNotes;
//		return this;
//	}
//
//}
