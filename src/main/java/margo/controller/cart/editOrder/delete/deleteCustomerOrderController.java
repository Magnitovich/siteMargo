package margo.controller.cart.editOrder.delete;

import margo.service.cart.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DeleteCustomerOrderController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/delete/customerOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody Long receiveIdForDelete(@RequestBody Long deleteOrderFromCustomer){

        System.out.println(deleteOrderFromCustomer);
        customerService.deleteCustomerOrder(deleteOrderFromCustomer);
        return deleteOrderFromCustomer;
    }
}
