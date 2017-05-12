package margo.controller.aboutFabric.delete;

import margo.model.modelDTO.allCurtainsDTO.TulleDTO;
import margo.service.fabric.TulleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Controller
public class deleteTulle {
    @Autowired
    private TulleService service;



    @RequestMapping(value = "/deleteTulle/DELETE", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView deleteCurtain(@RequestBody List<Long> namesDeleted){
    System.out.println(namesDeleted);

        service.delete(namesDeleted);
        List<TulleDTO> curtainDTOs = service.seeAllModels();
        ArrayList colorModel =service.seeColor();
        ArrayList paint =service.seePaint();
        ArrayList structure =service.seeStructure();
        ArrayList filterPrice = service.seePrice();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("price",filterPrice);
        modelAndView.addObject("forColor",colorModel);
        modelAndView.addObject("forPaint",paint);
        modelAndView.addObject("forStructure",structure);
        modelAndView.addObject("allTulle",curtainDTOs);
        modelAndView.setViewName("allFabric/tulleFabric");

        return modelAndView;


}
}
