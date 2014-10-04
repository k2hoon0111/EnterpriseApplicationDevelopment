package lv.javaguru.ee.warehouse.integrations.controllers;


import lv.javaguru.ee.warehouse.core.CommandExecutor;
import lv.javaguru.ee.warehouse.core.command.ProductCRUDCommand;
import lv.javaguru.ee.warehouse.core.command.ProductCommandResult;
import lv.javaguru.ee.warehouse.core.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dell
 */

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, value = "rest")
public class ProductController {
    
    @Autowired
    private CommandExecutor commandExecutor;

    @RequestMapping(value = "product", method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createProduct() {
    
//        ProductCRUDCommand command = new ProductCRUDCommand();
//        
//        ProductCommandResult result = commandExecutor.execute(command);
        
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }
    
//    @RequestMapping(method = RequestMethod.POST, value = "/rest/delivery/{deliveryId}/client")
//    public ResponseEntity<ClientDTO> createClient(@PathVariable Long deliveryId,
//                                                  @RequestBody ClientDTO clientDTO) {
//        CreateClientCommand command = createClientCommand(deliveryId, clientDTO);
//        CreateClientResult result = commandExecutor.execute(command);
//
//        Client client = result.getClient();
//        ClientDTO resultDTO = createClientDTO(client);
//
//        return new ResponseEntity<ClientDTO>(resultDTO, HttpStatus.CREATED);
//    }
//
//    private ClientDTO createClientDTO(Client client) {
//        return ClientDTOBuilder.createClientDTO()
//		        .withClientId(client.getClientId())
//		        .withFirstName(client.getFirstName())
//		        .withLastName(client.getLastName())
//		        .withEmail(client.getEmail())
//		        .withPhone(client.getPhone())
//		        .withSpecialNotes(client.getSpecialNotes()).build();
//    }
//
//    private CreateClientCommand createClientCommand(Long deliveryId, ClientDTO clientDTO) {
//        return new CreateClientCommand(deliveryId,
//                clientDTO.getFirstName(),
//                clientDTO.getLastName(),
//                clientDTO.getEmail(),
//                clientDTO.getPhone(),
//                clientDTO.getSpecialNotes()
//        );
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/rest/delivery/{deliveryId}/client/{clientId}")
//    public ResponseEntity<ClientDTO> getClient(@PathVariable Long deliveryId,
//                                               @PathVariable Long clientId) {
//        GetClientCommand command = new GetClientCommand(deliveryId, clientId);
//        GetClientResult result = commandExecutor.execute(command);
//        Client client = result.getClient();
//        ClientDTO clientDTO = createClientDTO(client);
//        return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.OK);
//    }
//    
    
}
