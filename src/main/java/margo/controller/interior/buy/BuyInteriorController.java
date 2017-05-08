package margo.controller.interior.buy;

import margo.model.interior.InteriorModel;
import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.service.finishedProduct.CheckRepositoryService;
import margo.service.finishedProduct.MainFinishedService;
import margo.service.interior.InteriorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BuyInteriorController {
    @Autowired
    private InteriorService interiorService;

    @RequestMapping(value = "/buyInteriorProduct", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeSelectedCurtain(@RequestParam(value = "modelId", required = false)Long id){

        ModelAndView modelAndView = new ModelAndView();

        InteriorModel selectedModel = interiorService.findSelectedModel(id);
        modelAndView.addObject("selectedCurtain", selectedModel);
        modelAndView.setViewName("interior/buy/buyInteriorProduct");
        return modelAndView;
    }
}
