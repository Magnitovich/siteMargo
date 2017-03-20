package margo.controller.aboutFabric.delete;

import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.fabric.CurtainService;
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
public class deleteCurtain {
    @Autowired
    private CurtainService curtainService;

    @RequestMapping(value = "/delete/DELETE", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView deleteCurtain(@RequestBody List<Long> namesDeleted, Model model,
                                      HttpServletRequest req){
//    System.out.println("Name delete: "+namesDeleted);

        curtainService.deleteCurtain(namesDeleted);

        List<CurtainDTO> curtainDTOs = curtainService.seeAllModels();
        ArrayList colorModel =curtainService.seeColor();
        ArrayList paint =curtainService.seePaint();
        ArrayList structure =curtainService.seeStructure();
        ArrayList filterPrice = curtainService.seePrice();
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
