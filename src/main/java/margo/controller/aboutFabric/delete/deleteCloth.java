package margo.controller.aboutFabric.delete;

import margo.model.modelDTO.allCurtainsDTO.ClothFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.fabric.ClothFabricService;
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
public class deleteCloth {
    @Autowired
    private ClothFabricService clothFabricService;

    @RequestMapping(value = "/deleteCloth/DELETE", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView deleteCurtain(@RequestBody List<Long> namesDeleted, Model model,
                                      HttpServletRequest req){
    System.out.println(namesDeleted);

        clothFabricService.deleteCloth(namesDeleted);

        List<ClothFabricDTO> clothFabricDTOs = clothFabricService.seeAllCloth();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allCloth", clothFabricDTOs);
        modelAndView.setViewName("allFabric/clothFabric");
        return modelAndView;

}
}
