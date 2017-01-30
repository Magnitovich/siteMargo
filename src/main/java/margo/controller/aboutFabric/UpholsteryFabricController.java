package margo.controller.aboutFabric;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UpholsteryFabricController {

    @RequestMapping(value = "/upholsteryFabric", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeAllUpholstery(){

        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }
}
