package margo.controller.cart.editOrder;

import margo.model.cartOder.cartDTO.CustomerDTO;
import margo.service.cart.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutCustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customerData", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView infoAboutCustomer(@RequestParam(value = "customerInfo")Long id){

        System.out.println("ID: "+id);
        ModelAndView modelAndView = new ModelAndView();
        CustomerDTO customerDTO = customerService.seeSelectedCustomer(id);
        modelAndView.addObject("selected", customerDTO);
        modelAndView.setViewName("cart/order/customerInfo");
        return modelAndView;
    }
}
