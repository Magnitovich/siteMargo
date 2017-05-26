package margo.controller.offers;

import margo.service.offer.MainOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OfferController {
    @Autowired
    private MainOfferService service;


    @RequestMapping(value = "offer", method = RequestMethod.GET)
    public ModelAndView seeOffer(){
        ModelAndView modelAndView = new ModelAndView();
//        System.out.println((service.allList()).size());
//        modelAndView.addObject("quantity", (service.allList()).size());
        modelAndView.setViewName("offers/offerSite");
        return modelAndView;
    }
}
