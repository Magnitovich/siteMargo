package margo.controller.aboutFabric;

import margo.model.modelDTO.allCurtainsDTO.ClothFabricDTO;
import margo.service.fabric.ClothFabricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClothController {

    @Autowired
    private ClothFabricService clothFabricService;

    @RequestMapping(value = "/clothModel", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeCloth(){

        List<ClothFabricDTO> clothFabricDTOs = clothFabricService.seeAllCloth();
        ArrayList colorModel =clothFabricService.seeColor();
        ArrayList paint =clothFabricService.seePaint();
        ArrayList structure =clothFabricService.seeStructure();
        ArrayList filterPrice = clothFabricService.seePrice();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("price",filterPrice);
        modelAndView.addObject("forColor",colorModel);
        modelAndView.addObject("forPaint",paint);
        modelAndView.addObject("forStructure",structure);
        modelAndView.setViewName("allFabric/tulleFabric");
        modelAndView.addObject("allCloth", clothFabricDTOs);
        modelAndView.setViewName("allFabric/clothFabric");
        return modelAndView;
    }
}
