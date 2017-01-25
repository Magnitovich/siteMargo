package margo.controller.aboutFabric;

import margo.model.modelDTO.allCurtainsDTO.*;
import margo.service.fabric.AllFabricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AllModelController {

    @Autowired
    private AllFabricService allFabricService;

    @RequestMapping(value = "/allModel", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView allModel(){

        List<CurtainDTO> curtainDTOs = allFabricService.seeCurtain();
        List<ClothFabricDTO> clothFabricDTOs = allFabricService.seeClothFabric();
        List<OrderCurtainDTO> orderCurtainDTOs = allFabricService.seeOrderCurtain();
        List<UpholsteryFabricDTO> upholsteryFabricDTOs = allFabricService.seeUpholsteryFabric();
        List<TulleDTO> tulleDTOs = allFabricService.seeTulle();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allCurtain", curtainDTOs);
        modelAndView.addObject("allCloth", clothFabricDTOs);
        modelAndView.addObject("allOrderCurtain", orderCurtainDTOs);
        modelAndView.addObject("allUp", upholsteryFabricDTOs);
        modelAndView.addObject("allTulle", tulleDTOs);

         modelAndView.setViewName("allFabric/allModel");


    return modelAndView;
    }
}
