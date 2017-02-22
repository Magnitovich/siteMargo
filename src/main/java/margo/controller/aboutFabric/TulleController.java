package margo.controller.aboutFabric;

import margo.model.modelDTO.allCurtainsDTO.TulleDTO;
import margo.service.fabric.TulleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TulleController {

    @Autowired
    private TulleService service;
    @RequestMapping(value = "/tulleModel", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeAllTulle(){

        List<TulleDTO> curtainDTOs = service.seeAllModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allTulle",curtainDTOs);
        modelAndView.setViewName("allFabric/tulleFabric");

        return modelAndView;
    }
}
