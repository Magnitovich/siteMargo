package margo.controller.interior;

import margo.model.interior.InteriorModel;
import margo.service.interior.InteriorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class InteriorController {

    @Autowired
    private InteriorService service;

    @RequestMapping(value = "interior", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeInterior(){

        ModelAndView modelAndView = new ModelAndView();
        Iterable<InteriorModel> interiorModels = service.seeAllInterior();
        ArrayList price = service.seePrice();
        ArrayList color = service.seeColor();
        String part = "pillow";
        modelAndView.addObject("namePage", part);
        modelAndView.addObject("allCurtain",interiorModels);
        modelAndView.addObject("price",price);
        modelAndView.addObject("forColor",color);
        modelAndView.setViewName("interior/interior");
        return modelAndView;
    }
}
