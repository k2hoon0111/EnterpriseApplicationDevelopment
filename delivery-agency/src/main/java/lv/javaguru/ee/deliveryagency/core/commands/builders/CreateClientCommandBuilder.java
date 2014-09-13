package lv.javaguru.ee.deliveryagency.core.commands.builders;

import lv.javaguru.ee.deliveryagency.core.commands.CreateClientCommand;

public class CreateClientCommandBuilder {

	private Long deliveryId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String specialNotes;

	
	public static CreateClientCommandBuilder createCreateClientCommand() {
		return new CreateClientCommandBuilder();
	}
	
	private CreateClientCommandBuilder() { }

	public CreateClientCommand build() {
		return new CreateClientCommand(deliveryId, firstName, lastName, email, phone, specialNotes);
	}

	public CreateClientCommandBuilder withDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
		return this;
	}
	
	public CreateClientCommandBuilder withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public CreateClientCommandBuilder withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public CreateClientCommandBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public CreateClientCommandBuilder withPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public CreateClientCommandBuilder withSpecialNotes(String specialNotes) {
		this.specialNotes = specialNotes;
		return this;
	}

}
