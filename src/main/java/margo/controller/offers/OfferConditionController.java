package margo.controller.offers;

import margo.controller.MainController;
import margo.service.offer.MainOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OfferConditionController {

    @Autowired
    private MainOfferService mainOfferService;
    @Autowired
    private MainController mainController;

    @RequestMapping(value = "informationForOffer", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView conditionOffer(@RequestParam(value = "percent")Double percent,
                                       @RequestParam(value = "quantityInOffer")Integer quantity
                                       ){
        mainOfferService.showAll(percent, quantity);
        System.out.println("OfferConditionController, percent: "+percent+ " quantity: "+quantity);
        return mainController.viewMainPage();
    }

}
