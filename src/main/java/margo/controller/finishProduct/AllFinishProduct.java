package margo.controller.finishProduct;

import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.service.finishedProduct.CheckRepositoryService;
import margo.service.finishedProduct.MainFinishedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AllFinishProduct {

    @Autowired
    private MainFinishedService service;
    @Autowired
    private CheckRepositoryService repositoryService;


    @RequestMapping(value = "finishProduct", method = RequestMethod.GET)
    public ModelAndView showBedroom(){
        ModelAndView modelAndView = new ModelAndView();

        List<AllFabricDTO> bedroom = service.seeAllModels(repositoryService.selectRepository("bedroom"));
        List<AllFabricDTO> cabinet = service.seeAllModels(repositoryService.selectRepository("cabinet"));
        List<AllFabricDTO> children = service.seeAllModels(repositoryService.selectRepository("children"));
        List<AllFabricDTO> guestroom = service.seeAllModels(repositoryService.selectRepository("guestroom"));
        List<AllFabricDTO> kitchen = service.seeAllModels(repositoryService.selectRepository("kitchen"));
        List<AllFabricDTO> lambr = service.seeAllModels(repositoryService.selectRepository("lambr"));
        List<AllFabricDTO> curtFinish = service.seeAllModels(repositoryService.selectRepository("curtFinish"));
        List<AllFabricDTO> tulleFinish = service.seeAllModels(repositoryService.selectRepository("tulleFinish"));

        modelAndView.addObject("allBedroom", bedroom);
        modelAndView.addObject("allCabinet", cabinet);
        modelAndView.addObject("allChildren", children);
        modelAndView.addObject("allGuest", guestroom);
        modelAndView.addObject("allKitchen", kitchen);
        modelAndView.addObject("allLambr", lambr);
        modelAndView.addObject("allCurtain", curtFinish);
        modelAndView.addObject("allTulle", tulleFinish);

        modelAndView.setViewName("finishedProduct/allFinishedProduct");


        return modelAndView;
    }
}
