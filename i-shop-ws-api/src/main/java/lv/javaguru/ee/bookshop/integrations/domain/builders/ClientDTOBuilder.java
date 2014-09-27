//package lv.javaguru.ee.deliveryagency.integrations.domain.builders;
//
//import lv.javaguru.ee.deliveryagency.integrations.domain.ClientDTO;
//
//public class ClientDTOBuilder {
//
//	private Long clientId;
//	private String firstName;
//	private String lastName;
//	private String email;
//	private String phone;
//	private String specialNotes;
//
//
//	public static ClientDTOBuilder createClientDTO() {
//		return new ClientDTOBuilder();
//	}
//
//	private ClientDTOBuilder() { }
//
//	public ClientDTO build() {
//		ClientDTO dto = new ClientDTO();
//		dto.setClientId(clientId);
//		dto.setFirstName(firstName);
//		dto.setLastName(lastName);
//		dto.setEmail(email);
//		dto.setPhone(phone);
//		dto.setSpecialNotes(specialNotes);
//		return dto;
//	}
//
//	public ClientDTOBuilder withClientId(Long clientId) {
//		this.clientId = clientId;
//		return this;
//	}
//
//	public ClientDTOBuilder withFirstName(String firstName) {
//		this.firstName = firstName;
//		return this;
//	}
//
//	public ClientDTOBuilder withLastName(String lastName) {
//		this.lastName = lastName;
//		return this;
//	}
//
//	public ClientDTOBuilder withEmail(String email) {
//		this.email = email;
//		return this;
//	}
//
//	public ClientDTOBuilder withPhone(String phone) {
//		this.phone = phone;
//		return this;
//	}
//
//	public ClientDTOBuilder withSpecialNotes(String specialNotes) {
//		this.specialNotes = specialNotes;
//		return this;
//	}
//
//}
