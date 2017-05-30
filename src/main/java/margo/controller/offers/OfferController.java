package margo.controller.offers;

import margo.service.offer.MainOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OfferController {
    @Autowired
    private MainOfferService service;


    @RequestMapping(value = "offer", method = RequestMethod.GET)
    public ModelAndView seeOffer(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allOffer", service.seeAllOffer());
        modelAndView.setViewName("offers/offerSite");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/offer", method = {RequestMethod.POST})
    public @ResponseBody
    List deleteCurtain(@RequestBody List<Long> namesDeleted){

        service.delete(namesDeleted);

        return namesDeleted;

    }

}
