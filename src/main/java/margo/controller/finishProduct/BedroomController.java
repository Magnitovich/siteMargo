package margo.controller.finishProduct;

import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.finishedProduct.BedroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BedroomController {

    @Autowired
    private BedroomService service;

    @RequestMapping(value = "bedroom", method = RequestMethod.GET)
    public ModelAndView showBedroom(){
        ModelAndView modelAndView = new ModelAndView();
        List<AllFabricDTO> curtainDTOs = service.seeAllModels();
        ArrayList colorModel =service.seeColor();
        ArrayList paint =service.seePaint();
        ArrayList structure =service.seeStructure();
        ArrayList filterPrice = service.seePrice();

        modelAndView.addObject("allCurtain",curtainDTOs);
        modelAndView.addObject("price",filterPrice);
        modelAndView.addObject("forColor",colorModel);
        modelAndView.addObject("forPaint",paint);
        modelAndView.addObject("forStructure",structure);

        modelAndView.setViewName("finishedProduct/bedroom");
        return modelAndView;
    }
}
