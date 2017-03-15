package margo.controller.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AllCartController {


    @RequestMapping(value = "allCart", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView view() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cart/cartStart");
        return modelAndView;

    }
}
