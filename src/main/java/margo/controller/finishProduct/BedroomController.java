package margo.controller.finishProduct;

import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.service.finishedProduct.CheckRepositoryService;
import margo.service.finishedProduct.MainFinishedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BedroomController {

    @Autowired
    private MainFinishedService service;
    @Autowired
    private CheckRepositoryService repositoryService;

    private CrudRepository repository;

    public CrudRepository getRepository() {
        return repository;
    }

    @RequestMapping(value = "/finishProducts", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView showBedroom(@RequestParam(value = "part", required = false)String part){
        repository = repositoryService.selectRepository(part);
//        System.out.println("Bedroom(/finishProducts) controller part: "+part);
        ModelAndView modelAndView = new ModelAndView();
        List<AllFabricDTO> curtainDTOs = service.seeAllModels(repository);
        ArrayList colorModel =service.seeColor();
        ArrayList paint =service.seePaint();
        ArrayList structure =service.seeStructure();
        ArrayList sewed =service.seeSewed();
        ArrayList filterPrice = service.seePrice();

        modelAndView.addObject("namePage", part);
        modelAndView.addObject("allCurtain",curtainDTOs);
        modelAndView.addObject("price",filterPrice);
        modelAndView.addObject("forColor",colorModel);
        modelAndView.addObject("forPaint",paint);
        modelAndView.addObject("forStructure",structure);
        modelAndView.addObject("forSewed",sewed);

        modelAndView.setViewName("finishedProduct/bedroom");
        return modelAndView;
    }
}
