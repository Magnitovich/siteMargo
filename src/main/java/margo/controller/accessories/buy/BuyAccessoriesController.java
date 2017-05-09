package margo.controller.accessories.buy;

import margo.model.interior.InteriorModel;
import margo.model.modelDTO.accessories.AccessoriesDTO;
import margo.service.accessories.AccessoriesService;
import margo.service.accessories.CheckAccessoriesRepositoryService;
import margo.service.interior.InteriorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BuyAccessoriesController {
    @Autowired
    private AccessoriesService accessoriesService;
    @Autowired
    private CheckAccessoriesRepositoryService checkAccessoriesRepositoryService;

    @RequestMapping(value = "/buyAccessoriesProduct", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeSelectedCurtain(@RequestParam(value = "modelId", required = false)Long id,
                                           @RequestParam(value = "part", required = false)String part){

        ModelAndView modelAndView = new ModelAndView();
        AccessoriesDTO selectedModel = accessoriesService.findSelectedModel(id,
                                                        checkAccessoriesRepositoryService.selectRepository(part));
        modelAndView.addObject("selectedCurtain", selectedModel);
        modelAndView.setViewName("accessories/buy/buyAccessoriesProduct");
        return modelAndView;
    }
}
