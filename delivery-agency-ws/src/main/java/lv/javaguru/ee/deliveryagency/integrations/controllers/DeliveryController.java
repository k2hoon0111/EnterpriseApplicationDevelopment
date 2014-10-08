package lv.javaguru.ee.deliveryagency.integrations.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import lv.javaguru.ee.deliveryagency.core.CommandExecutor;
import lv.javaguru.ee.deliveryagency.core.commands.delivery.CreateDeliveryCommand;
import lv.javaguru.ee.deliveryagency.core.commands.delivery.CreateDeliveryResult;
import lv.javaguru.ee.deliveryagency.core.commands.delivery.GetDeliveryCommand;
import lv.javaguru.ee.deliveryagency.core.commands.delivery.GetDeliveryResult;
import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;

import javax.ws.rs.Produces;

@Controller
@RequestMapping("/rest/delivery")
@Api(value="delivery", description="Delivery entity operations")
public class DeliveryController {
	
	@Autowired
	private CommandExecutor commandExecutor;


	@RequestMapping(method = RequestMethod.POST)
    @Produces("application/xml")
	@ApiOperation(value = "Create new delivery object")
	public ResponseEntity<DeliveryDTO> createDelivery(@ApiParam(name="deliveryDTO", value="Delivery information")
													  @RequestBody DeliveryDTO deliveryDTO) {
		CreateDeliveryCommand createDeliveryCommand = new CreateDeliveryCommand();
		CreateDeliveryResult createDeliveryResult = commandExecutor.execute(createDeliveryCommand);

        Delivery delivery = createDeliveryResult.getDelivery();
        deliveryDTO.setDeliveryId(delivery.getDeliveryId());
		
		return new ResponseEntity<DeliveryDTO>(deliveryDTO, HttpStatus.CREATED);
	}

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @Produces("application/xml")
    @ApiOperation(value = "Get specific delivery object by its id")
    public ResponseEntity<DeliveryDTO> getDelivery(@ApiParam(name="id", value="Delivery id")
                                                   @PathVariable Long id) {
        GetDeliveryCommand command = new GetDeliveryCommand(id);
        GetDeliveryResult result = commandExecutor.execute(command);
        Delivery delivery = result.getDelivery();

        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setDeliveryId(delivery.getDeliveryId());

        return new ResponseEntity(deliveryDTO, HttpStatus.OK);
    }

}
