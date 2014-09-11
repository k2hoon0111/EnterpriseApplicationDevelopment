package lv.javaguru.ee.deliveryagency.core.commands;

import lv.javaguru.ee.deliveryagency.core.DomainCommand;

/**
 * Created by Viktor on 08/09/2014.
 */
public class CreateClientCommand implements DomainCommand {

    private Long deliveryId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String specialNotes;

    public CreateClientCommand(Long deliveryId,
                               String firstName,
                               String lastName,
                               String email,
                               String phone,
                               String specialNotes) {
        this.deliveryId = deliveryId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.specialNotes = specialNotes;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

}
