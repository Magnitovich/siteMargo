package margo.controller.cart.editOrder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EditOrderCustomerController {

    @RequestMapping(value = "/editInfoFromCustomerOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView editCustomerOrder(){
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }
}
