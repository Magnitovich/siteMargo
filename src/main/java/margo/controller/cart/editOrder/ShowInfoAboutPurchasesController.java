package margo.controller.cart.editOrder;

import margo.model.cartOder.cartDTO.CustomerDTO;
import margo.service.cart.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ShowInfoAboutPurchasesController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping(value = "administrationNotSleeps", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView showFirst(){

        ModelAndView modelAndView = new ModelAndView();
        List<CustomerDTO> customerDTOs = customerService.showAllCustomer();
        modelAndView.addObject("selected", customerDTOs);
        modelAndView.setViewName("cart/order/checkOrder");
        return modelAndView;
    }
}
