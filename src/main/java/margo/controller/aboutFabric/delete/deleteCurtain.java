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
import java.util.List;

@Controller
public class deleteCurtain {
    @Autowired
    private CurtainService curtainService;

    @RequestMapping(value = "/delete/DELETE", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView deleteCurtain(@RequestBody List<Long> namesDeleted, Model model,
                                      HttpServletRequest req){
    System.out.println(namesDeleted);

        curtainService.deleteCurtain(namesDeleted);

        List<CurtainDTO> curtainDTOs = curtainService.seeAllModels();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allCurtain",curtainDTOs);
        modelAndView.setViewName("allFabric/curtainModel");
    return modelAndView;

}
}