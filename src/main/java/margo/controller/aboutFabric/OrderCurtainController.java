package margo.controller.aboutFabric;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderCurtainController {

    @RequestMapping(value = "/orderFabric", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeAllOrder(){

        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }
}
