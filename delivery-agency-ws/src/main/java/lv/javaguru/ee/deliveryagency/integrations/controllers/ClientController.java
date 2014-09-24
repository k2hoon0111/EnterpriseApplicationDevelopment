package lv.javaguru.ee.deliveryagency.integrations.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lv.javaguru.ee.deliveryagency.core.CommandExecutor;
import lv.javaguru.ee.deliveryagency.core.commands.CreateClientCommand;
import lv.javaguru.ee.deliveryagency.core.commands.CreateClientResult;
import lv.javaguru.ee.deliveryagency.core.commands.GetClientCommand;
import lv.javaguru.ee.deliveryagency.core.commands.GetClientResult;
import lv.javaguru.ee.deliveryagency.core.domain.Client;
import lv.javaguru.ee.deliveryagency.integrations.domain.ClientDTO;
import lv.javaguru.ee.deliveryagency.integrations.domain.builders.ClientDTOBuilder;

/**
 * Created by Viktor on 16/09/2014.
 */
@Controller
public class ClientController {

    @Autowired
    private CommandExecutor commandExecutor;


    @RequestMapping(method = RequestMethod.POST, value = "/rest/delivery/{deliveryId}/client")
    public ResponseEntity<ClientDTO> createClient(@PathVariable Long deliveryId,
                                                  @RequestBody ClientDTO clientDTO) {
        CreateClientCommand command = createClientCommand(deliveryId, clientDTO);
        CreateClientResult result = commandExecutor.execute(command);

        Client client = result.getClient();
        ClientDTO resultDTO = createClientDTO(client);

        return new ResponseEntity<ClientDTO>(resultDTO, HttpStatus.CREATED);
    }

    private ClientDTO createClientDTO(Client client) {
        return ClientDTOBuilder.createClientDTO()
		        .withClientId(client.getClientId())
		        .withFirstName(client.getFirstName())
		        .withLastName(client.getLastName())
		        .withEmail(client.getEmail())
		        .withPhone(client.getPhone())
		        .withSpecialNotes(client.getSpecialNotes()).build();
    }

    private CreateClientCommand createClientCommand(Long deliveryId, ClientDTO clientDTO) {
        return new CreateClientCommand(deliveryId,
                clientDTO.getFirstName(),
                clientDTO.getLastName(),
                clientDTO.getEmail(),
                clientDTO.getPhone(),
                clientDTO.getSpecialNotes()
        );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rest/delivery/{deliveryId}/client/{clientId}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long deliveryId,
                                               @PathVariable Long clientId) {
        GetClientCommand command = new GetClientCommand(deliveryId, clientId);
        GetClientResult result = commandExecutor.execute(command);
        Client client = result.getClient();
        ClientDTO clientDTO = createClientDTO(client);
        return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.OK);
    }

}
