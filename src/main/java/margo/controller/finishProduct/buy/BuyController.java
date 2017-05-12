package margo.controller.finishProduct.buy;

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

@Controller
public class BuyController {
    @Autowired
    private CheckRepositoryService repositoryService;
    @Autowired
    private MainFinishedService mainFinishedService;

    @RequestMapping(value = "/buyFinishProduct", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeSelectedCurtain(@RequestParam(value = "modelId", required = false)Long id,
                                           @RequestParam(value = "part", required = false)String part){

        ModelAndView modelAndView = new ModelAndView();
        CrudRepository crudRepository = repositoryService.selectRepository(part);
        AllFabricDTO selectedModel = mainFinishedService.findSelectedModel(id, crudRepository);

        modelAndView.addObject("selectedCurtain", selectedModel);
        modelAndView.setViewName("finishedProduct/buy/buyFinishProduct");
        return modelAndView;
    }
}
