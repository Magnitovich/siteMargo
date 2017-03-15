package margo.controller.cart.editOrder;

import margo.model.cartOder.cartDTO.CustomerDTO;
import margo.model.cartOder.cartDTO.OrderCustomerDTO;
import margo.service.cart.CustomerService;
import margo.service.cart.OrderCustomerService;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrederFromCustomerController {

    @Autowired
    private OrderCustomerService orderCustomerService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/orderFromCustomer", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView infoAboutCustomer(@RequestParam(value = "orderCustomer")Long id){

        ModelAndView modelAndView = new ModelAndView();
        List<OrderCustomerDTO> orderCustomerDTOs = orderCustomerService.seeSelectedCustomer(id);
        CustomerDTO customerDTO = customerService.seeSelectedCustomer(id);
        modelAndView.addObject("customerInfo", customerDTO);
        modelAndView.addObject("selected", orderCustomerDTOs);
        modelAndView.setViewName("cart/order/customerOrder");
        return modelAndView;

    }
}
