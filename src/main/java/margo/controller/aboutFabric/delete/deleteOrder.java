package margo.controller.aboutFabric.delete;

import margo.model.modelDTO.allCurtainsDTO.OrderCurtainDTO;
import margo.model.modelDTO.allCurtainsDTO.TulleDTO;
import margo.service.fabric.OrderCurtainService;
import margo.service.fabric.TulleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class deleteOrder {
    @Autowired
    private OrderCurtainService service;



    @RequestMapping(value = "/deleteOrder/DELETE", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView deleteCurtain(@RequestBody List<Long> namesDeleted){
    System.out.println(namesDeleted);

        service.delete(namesDeleted);
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
        modelAndView.addObject("allOrder",curtainDTOs);
        modelAndView.setViewName("allFabric/orderFabric");

        return modelAndView;


}
}
