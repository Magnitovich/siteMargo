package margo.controller;

import margo.controller.aboutFabric.buy.BuyCurtainController;
import margo.controller.accessories.buy.BuyAccessoriesController;
import margo.controller.finishProduct.buy.BuyController;
import margo.controller.interior.buy.BuyInteriorController;
import margo.model.modelDTO.allCurtainsDTO.*;
import margo.service.fabric.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BuyFromSearchController {
    @Autowired
    private BuyCurtainController buyCurtainController;

    @Autowired
    private BuyAccessoriesController buyAccessoriesController;
    @Autowired
    private BuyController buyFinishController;
    @Autowired
    private BuyInteriorController buyInteriorController;

    @RequestMapping(value = "/buySelectedFromSearch", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeSelectedCurtain(@RequestParam(value = "id", required = false) Long id,
                                           @RequestParam(value = "part") String photo) {
//        System.out.println("REQUEST ID: " + id);
//        System.out.println("Photo: " + photo);
        String[] split = photo.split("/");
//        System.out.println("split[0] " + split[0] + " split[1] " + split[1] + " split[2] " + split[2]
//                + " split[3] " + split[3]);
        ModelAndView modelAndView = new ModelAndView();
        switch (split[1]) {
            case "accessories":
                modelAndView = buyAccessoriesController.seeSelectedCurtain(id, split[2]);
                break;
            case "finishProduct":
                modelAndView = buyFinishController.seeSelectedCurtain(id, split[2]);
                break;
            case "interior":
                modelAndView = buyInteriorController.seeSelectedCurtain(id);
                break;
            case "allFabric":
                modelAndView = buyCurtainController.seeSelectedCurtain(id, split[2]);
                break;
                }

        return modelAndView;
    }
}