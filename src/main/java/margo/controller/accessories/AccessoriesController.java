package margo.controller.accessories;

import margo.model.modelDTO.accessories.AccessoriesDTO;
import margo.service.accessories.AccessoriesService;
import margo.service.accessories.CheckAccessoriesRepositoryService;
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
public class AccessoriesController {

    @Autowired
    private AccessoriesService service;
    @Autowired
    private CheckAccessoriesRepositoryService repositoryService;

    private CrudRepository repository;

    public CrudRepository getRepository() {
        return repository;
    }

    @RequestMapping(value = "/accessoriesProduct", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView showBedroom(@RequestParam(value = "part", required = false)String part){
        ModelAndView modelAndView = new ModelAndView();
        if (part != null) {
            repository = repositoryService.selectRepository(part);
            List<AccessoriesDTO> curtainDTOs = service.seeAllModels(repository);
            ArrayList colorModel = service.seeColor();
            ArrayList filterPrice = service.seePrice();

            modelAndView.addObject("namePage", part);
            modelAndView.addObject("allCurtain", curtainDTOs);
            modelAndView.addObject("price", filterPrice);
            modelAndView.addObject("forColor", colorModel);
        } else {
            List<AccessoriesDTO> accessoriesDTOs = service.seeAll();
            ArrayList colorModel = service.seeColor();
            ArrayList filterPrice = service.seePrice();

            modelAndView.addObject("namePage", "allAccessories");
            modelAndView.addObject("allCurtain", accessoriesDTOs);
            modelAndView.addObject("price", filterPrice);
            modelAndView.addObject("forColor", colorModel);
        }
        modelAndView.setViewName("accessories/accessories");
        return modelAndView;
    }
}
