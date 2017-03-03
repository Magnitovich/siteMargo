package margo.controller.aboutFabric;

import margo.model.modelDTO.allCurtainsDTO.OrderCurtainDTO;
import margo.model.modelDTO.allCurtainsDTO.TulleDTO;
import margo.model.modelDTO.allCurtainsDTO.UpholsteryFabricDTO;
import margo.service.fabric.OrderCurtainService;
import margo.service.fabric.UpholsteryFabricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderCurtainController {

    @Autowired
    private OrderCurtainService service;

    @RequestMapping(value = "/orderFabric", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeAllOrder(){
        List<OrderCurtainDTO> curtainDTOs = service.seeAllModels();
        ArrayList colorModel =service.seeColor();
        ArrayList paint =service.seePaint();
        ArrayList structure =service.seeStructure();
        ArrayList filterPrice = service.seePrice();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("price",filterPrice);
        modelAndView.addObject("forColor",colorModel);
        modelAndView.addObject("forPaint",paint);
        modelAndView.addObject("forStructure",structure);
        modelAndView.setViewName("allFabric/tulleFabric");
        modelAndView.addObject("allOrder",curtainDTOs);
        modelAndView.setViewName("allFabric/orderFabric");
        return modelAndView;
    }
}
