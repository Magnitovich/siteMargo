package margo.controller.aboutFabric;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TulleController {

    @RequestMapping(value = "/tulleModel", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeAllTulle(){

        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }
}
