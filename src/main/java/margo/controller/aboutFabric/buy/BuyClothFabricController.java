package margo.controller.aboutFabric.buy;

import margo.model.modelDTO.allCurtainsDTO.ClothFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.fabric.ClothFabricService;
import margo.service.fabric.CurtainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BuyClothFabricController {
    @Autowired
    private ClothFabricService service;

    @RequestMapping(value = "/buyClothFabric", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeSelectedCurtain(@RequestParam(value = "hiddenModelId", required = false)Long id){
//        System.out.println("REQUEST ID: "+id);

        ModelAndView modelAndView = new ModelAndView();
        String s = service.convertIdToName(id);
        List<ClothFabricDTO> dtos = service.viewName(s);
        modelAndView.addObject("selected", dtos);
        modelAndView.setViewName("allFabric/buyFabric/buyClothFabric");
        return modelAndView;
    }
}
