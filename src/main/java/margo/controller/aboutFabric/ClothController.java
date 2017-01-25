package margo.controller.aboutFabric;

import margo.model.modelDTO.allCurtainsDTO.ClothFabricDTO;
import margo.service.fabric.ClothFabricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ClothController {

    @Autowired
    private ClothFabricService clothFabricService;

    @RequestMapping(value = "/clothModel", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeCloth(){

        List<ClothFabricDTO> clothFabricDTOs = clothFabricService.seeAllCloth();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allCloth", clothFabricDTOs);
        modelAndView.setViewName("allFabric/clothFabric");
        return modelAndView;
    }
}
