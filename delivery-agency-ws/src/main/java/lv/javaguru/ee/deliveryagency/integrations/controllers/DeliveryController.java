package lv.javaguru.ee.deliveryagency.integrations.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import lv.javaguru.ee.deliveryagency.core.CommandExecutor;
import lv.javaguru.ee.deliveryagency.core.commands.CreateDeliveryCommand;
import lv.javaguru.ee.deliveryagency.core.commands.CreateDeliveryResult;
import lv.javaguru.ee.deliveryagency.core.commands.GetDeliveryCommand;
import lv.javaguru.ee.deliveryagency.core.commands.GetDeliveryResult;
import lv.javaguru.ee.deliveryagency.core.domain.Delivery;
import lv.javaguru.ee.deliveryagency.integrations.domain.DeliveryDTO;

@Controller
@Api(value="delivery", description="Delivery entity operations")
public class DeliveryController {
	
	@Autowired
	private CommandExecutor commandExecutor;


	@RequestMapping(method = RequestMethod.POST, value = "/rest/delivery")
	@ApiOperation(value = "Create new delivery object")
	public ResponseEntity<DeliveryDTO> createDelivery(@ApiParam(name="deliveryDTO", value="Delivery information")
													  @RequestBody DeliveryDTO deliveryDTO) {
		CreateDeliveryCommand createDeliveryCommand = new CreateDeliveryCommand();
		CreateDeliveryResult createDeliveryResult = commandExecutor.execute(createDeliveryCommand);

        Delivery delivery = createDeliveryResult.getDelivery();
        deliveryDTO.setDeliveryId(delivery.getDeliveryId());
		
		return new ResponseEntity<DeliveryDTO>(deliveryDTO, HttpStatus.CREATED);
	}

    @RequestMapping(method = RequestMethod.GET, value = "/rest/delivery/{id}")
    @ApiOperation(value = "Get specific delivery object by its id")
    public @ResponseBody DeliveryDTO getDelivery(@ApiParam(name="id", value="Delivery id")
                                                 @PathVariable Long id) {
        GetDeliveryCommand command = new GetDeliveryCommand(id);
        GetDeliveryResult result = commandExecutor.execute(command);
        Delivery delivery = result.getDelivery();

        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setDeliveryId(delivery.getDeliveryId());

        return deliveryDTO;
    }

}
