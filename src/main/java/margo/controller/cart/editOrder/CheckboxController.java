package margo.controller.cart.editOrder;

import margo.model.cartOder.cartDTO.CustomerDTO;
import margo.service.cart.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CheckboxController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/saveChangeCheckbox", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody Long  changeValue(@RequestParam(value = "idCustomer")Long id,
                                           @RequestParam(value = "receiveCheckbox")Boolean receiveOrder,
                                           @RequestParam(value = "sentCheckbox")Boolean sentOrder){
        customerService.changeCheckbox(id, receiveOrder, sentOrder);
//        System.out.println("editOrder, CheckboxController Id: "+id+" CheckboxReceive: "+receiveOrder+" Sent: "+sentOrder);
        return id;
    }
}
