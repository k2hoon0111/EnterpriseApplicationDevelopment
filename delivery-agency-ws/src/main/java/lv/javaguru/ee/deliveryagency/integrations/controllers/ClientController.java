package lv.javaguru.ee.deliveryagency.integrations.controllers;

import lv.javaguru.ee.deliveryagency.core.commands.client.DeleteClientCommand;
import lv.javaguru.ee.deliveryagency.core.commands.client.DeleteClientResult;
import lv.javaguru.ee.deliveryagency.core.commands.client.UpdateClientCommand;
import lv.javaguru.ee.deliveryagency.core.commands.client.UpdateClientResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lv.javaguru.ee.deliveryagency.core.CommandExecutor;
import lv.javaguru.ee.deliveryagency.core.commands.client.CreateClientCommand;
import lv.javaguru.ee.deliveryagency.core.commands.client.CreateClientResult;
import lv.javaguru.ee.deliveryagency.core.commands.client.GetClientCommand;
import lv.javaguru.ee.deliveryagency.core.commands.client.GetClientResult;
import lv.javaguru.ee.deliveryagency.core.domain.Client;
import lv.javaguru.ee.deliveryagency.integrations.domain.ClientDTO;
import lv.javaguru.ee.deliveryagency.integrations.domain.builders.ClientDTOBuilder;

import javax.ws.rs.Produces;

/**
 * Created by Viktor on 16/09/2014.
 */
@Controller
@RequestMapping("/rest/delivery/{deliveryId}/client")
public class ClientController {

    @Autowired
    private CommandExecutor commandExecutor;


    @RequestMapping(method = RequestMethod.POST)
    @Produces("application/xml")
    public ResponseEntity<ClientDTO> createClient(@PathVariable Long deliveryId,
                                                  @RequestBody ClientDTO clientDTO) {
        CreateClientCommand command = createClientCommand(deliveryId, clientDTO);
        CreateClientResult result = commandExecutor.execute(command);

        Client client = result.getClient();
        ClientDTO resultDTO = createClientDTO(client);

        return new ResponseEntity<ClientDTO>(resultDTO, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{clientId}")
    @Produces("application/xml")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long deliveryId,
                                                  @PathVariable Long clientId,
                                                  @RequestBody ClientDTO clientDTO) {
        UpdateClientCommand command = updateClientCommand(deliveryId, clientId, clientDTO);
        UpdateClientResult result = commandExecutor.execute(command);

        Client client = result.getClient();
        ClientDTO resultDTO = createClientDTO(client);

        return new ResponseEntity<ClientDTO>(resultDTO, HttpStatus.OK);
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

    private UpdateClientCommand updateClientCommand(Long deliveryId,
                                                    Long clientId,
                                                    ClientDTO clientDTO) {
        return new UpdateClientCommand(deliveryId, clientId,
                clientDTO.getFirstName(),
                clientDTO.getLastName(),
                clientDTO.getEmail(),
                clientDTO.getPhone(),
                clientDTO.getSpecialNotes()
        );
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{clientId}")
    @Produces("application/xml")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long deliveryId,
                                               @PathVariable Long clientId) {
        GetClientCommand command = new GetClientCommand(deliveryId, clientId);
        GetClientResult result = commandExecutor.execute(command);
        Client client = result.getClient();

        if(client == null) {
            return new ResponseEntity<ClientDTO>(HttpStatus.NOT_FOUND);
        }

        ClientDTO clientDTO = createClientDTO(client);
        return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{clientId}")
    @Produces("application/xml")
    public ResponseEntity<ClientDTO> deleteClient(@PathVariable Long deliveryId,
                                                  @PathVariable Long clientId) {
        DeleteClientCommand command = new DeleteClientCommand(deliveryId, clientId);
        DeleteClientResult result = commandExecutor.execute(command);
        Client client = result.getClient();

        if(client == null) {
            return new ResponseEntity<ClientDTO>(HttpStatus.NOT_FOUND);
        }

        ClientDTO clientDTO = createClientDTO(client);
        return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.OK);
    }

}
