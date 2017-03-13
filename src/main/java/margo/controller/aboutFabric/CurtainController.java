package margo.controller.aboutFabric;

import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.fabric.CurtainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CurtainController {

    @Autowired
    private CurtainService curtainService;

    @RequestMapping(value = "/curtainModels", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeAllCurtain(){
        List<CurtainDTO> curtainDTOs = curtainService.seeAllModels();
        ArrayList colorModel =curtainService.seeColor();
        ArrayList paint =curtainService.seePaint();
        ArrayList structure =curtainService.seeStructure();
        ArrayList filterPrice = curtainService.seePrice();
//        System.out.println(colorModel);
//        System.out.println(paint);
//        System.out.println(structure);
        for(CurtainDTO dto:curtainDTOs){
            String k = dto.getDescription();
            System.out.println("TextArea:  "+k);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allCurtain",curtainDTOs);
        modelAndView.addObject("price",filterPrice);
        modelAndView.addObject("forColor",colorModel);
        modelAndView.addObject("forPaint",paint);
        modelAndView.addObject("forStructure",structure);
        modelAndView.setViewName("allFabric/curtainModel");
        return modelAndView;
    }
}
