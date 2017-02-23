package margo.controller.aboutFabric.buy;

import margo.model.modelDTO.allCurtainsDTO.ClothFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.TulleDTO;
import margo.service.fabric.ClothFabricService;
import margo.service.fabric.TulleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BuyTulleFabricController {
    @Autowired
    private TulleService service;

    @RequestMapping(value = "/buyTulleFabric", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeSelectedCurtain(@RequestParam(value = "hiddenModelId", required = false)Long id){
//        System.out.println("REQUEST ID: "+id);

        ModelAndView modelAndView = new ModelAndView();
        String s = service.convertIdToName(id);
        List<TulleDTO> dtos = service.viewName(s);
        modelAndView.addObject("selected", dtos);
        modelAndView.setViewName("allFabric/buyFabric/buyTulleFabric");
        return modelAndView;
    }
}
