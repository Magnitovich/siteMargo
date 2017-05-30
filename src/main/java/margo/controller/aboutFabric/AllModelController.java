package margo.controller.aboutFabric;

import margo.model.modelDTO.allCurtainsDTO.*;
import margo.service.finishedProduct.MainFinishedService;
import margo.service.offer.SelectRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AllModelController {

    @Autowired
    private MainFinishedService service;
    @Autowired
    private SelectRepositoryService repositoryService;


    @RequestMapping(value = "/allModel", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView allModel(){

        List<AllFabricDTO> clothFabric = service.seeAllModels(repositoryService.selectRepository("clothFabric"));
        List<AllFabricDTO> curtain = service.seeAllModels(repositoryService.selectRepository("curtain"));
        List<AllFabricDTO> orderCurtain = service.seeAllModels(repositoryService.selectRepository("orderCurtain"));
        List<AllFabricDTO> tulle = service.seeAllModels(repositoryService.selectRepository("tulle"));
        List<AllFabricDTO> upholsteryFabric = service.seeAllModels(repositoryService.selectRepository("upholsteryFabric"));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allCurtain", curtain);
        modelAndView.addObject("allCloth", clothFabric);
        modelAndView.addObject("allOrderCurtain", orderCurtain);
        modelAndView.addObject("allUp", upholsteryFabric);
        modelAndView.addObject("allTulle", tulle);

         modelAndView.setViewName("allFabric/allModel");

    return modelAndView;
    }
}
