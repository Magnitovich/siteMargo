package margo.controller.aboutFabric.buy;

import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.fabric.CurtainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BuyCurtainController {
    @Autowired
    private CurtainService curtainService;

    @RequestMapping(value = "/buyCurtain", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeSelectedCurtain(@RequestParam(value = "hiddenModelId", required = false)Long id){

        ModelAndView modelAndView = new ModelAndView();
        String s = curtainService.convertIdToName(id);
        List<CurtainDTO> curtainDTOs = curtainService.viewName(s);
        modelAndView.addObject("selectedCurtain", curtainDTOs);
        modelAndView.setViewName("allFabric/buyFabric/buyCurtain");
        return modelAndView;
    }
}
