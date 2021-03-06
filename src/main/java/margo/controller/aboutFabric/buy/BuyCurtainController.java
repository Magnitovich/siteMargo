package margo.controller.aboutFabric.buy;

import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.fabric.CurtainService;
import margo.service.finishedProduct.MainFinishedService;
import margo.service.offer.SelectRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BuyCurtainController {
    @Autowired
    private SelectRepositoryService repositoryService;
    @Autowired
    private MainFinishedService mainFinishedService;

    @RequestMapping(value = "/buyCurtain", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeSelectedCurtain(@RequestParam(value = "modelId", required = false)Long id,
                                           @RequestParam(value = "part", required = false)String part){
        ModelAndView modelAndView = new ModelAndView();
        CrudRepository crudRepository = repositoryService.selectRepository(part);
        AllFabricDTO selectedModel = mainFinishedService.findSelectedModel(id, crudRepository);
        modelAndView.addObject("selectedCurtain", selectedModel);
        modelAndView.setViewName("allFabric/buyFabric/buyCurtain");
        return modelAndView;
    }
}
