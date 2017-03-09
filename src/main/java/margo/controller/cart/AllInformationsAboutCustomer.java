package margo.controller.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AllInformationsAboutCustomer {
    @RequestMapping(value = "/showAll", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView showAllForCustomer(@RequestParam(value = "NickName")String name,
                                           @RequestParam(value = "Phone")String phone,
                                           @RequestParam(value = "description")String addressDelivery,
                                           @RequestParam(value = "Email")String email){{
        System.out.println("Name: "+name+" Phone: "+phone+" Address: "+addressDelivery+" Email: "+email);

        ModelAndView modelAndView = new ModelAndView();


        return modelAndView;
    }

    }
}
