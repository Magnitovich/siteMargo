package margo.controller.finishProduct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GuestController {

    @RequestMapping(value = "guestroom", method = RequestMethod.GET)
    public ModelAndView showBedroom(){
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }
}
