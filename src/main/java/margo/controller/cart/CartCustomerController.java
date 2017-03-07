package margo.controller.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartCustomerController {
    @RequestMapping(value = "cartCustomer", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView view() {
        ModelAndView modelAndView = new ModelAndView();
//        String  email = receiveEmailFromSecurity.getEmail();
//        System.out.println(email);
//        modelAndView.addObject("email", email);
        modelAndView.setViewName("cart/cartInfoAboutCustomer");
        return modelAndView;

    }
}
