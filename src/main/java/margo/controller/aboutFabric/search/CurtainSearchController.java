package margo.controller.aboutFabric.search;

import margo.controller.aboutFabric.CurtainController;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.fabric.CurtainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CurtainSearchController {
    @Autowired
    private CurtainService curtainService;

    @RequestMapping(value = "/searchCurtain", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView searchCurtain(@ModelAttribute("searchCurtain")CurtainDTO curtainDTO,  BindingResult result,
            @RequestParam(value = "searchPrice", required = false)Integer price){
        System.out.println("PRICE IS: "+price);
        List<CurtainDTO> curtainDTOs = curtainService.seeAllModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allCurtain",curtainDTOs);
        modelAndView.setViewName("allFabric/curtainModel");
        return modelAndView;
    }

    @ModelAttribute("searchSelectedCurtain")
    public CurtainDTO createModel() {
        return new CurtainDTO();
    }
}
