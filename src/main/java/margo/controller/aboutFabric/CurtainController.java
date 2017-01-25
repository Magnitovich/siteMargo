package margo.controller.aboutFabric;

import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.fabric.CurtainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CurtainController {

    @Autowired
    private CurtainService curtainService;

    @RequestMapping(value = "/curtainModels", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeAllCurtain(){
        List<CurtainDTO> curtainDTOs = curtainService.seeAllModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allCurtain",curtainDTOs);
        modelAndView.setViewName("allFabric/curtainModel");
        return modelAndView;
    }
}
