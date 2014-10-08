package lv.javaguru.ee.deliveryagency.core.commands.client;

import lv.javaguru.ee.deliveryagency.core.commands.DomainCommand;

/**
 * Created by Viktor on 05/10/2014.
 */
public class UpdateClientCommand implements DomainCommand<UpdateClientResult> {

    private Long deliveryId;
    private Long clientId;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String specialNotes;


    public UpdateClientCommand(Long deliveryId,
                               Long clientId,
                               String firstName,
                               String lastName,
                               String email,
                               String phone,
                               String specialNotes) {
        this.deliveryId = deliveryId;
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.specialNotes = specialNotes;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public Long getClientId() {
        return clientId;
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
