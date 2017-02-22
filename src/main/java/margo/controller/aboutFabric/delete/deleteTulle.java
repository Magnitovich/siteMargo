package margo.controller.aboutFabric.delete;

import margo.controller.aboutFabric.TulleController;
import margo.model.modelDTO.allCurtainsDTO.ClothFabricDTO;
import margo.service.fabric.ClothFabricService;
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
public class deleteTulle {
    @Autowired
    private ClothFabricService clothFabricService;

    @Autowired
    private TulleController tulleController;

    @RequestMapping(value = "/deleteTulle/DELETE", method = {RequestMethod.GET, RequestMethod.POST})
    public void deleteCurtain(@RequestBody List<Long> namesDeleted, Model model,
                                      HttpServletRequest req){
    System.out.println(namesDeleted);

        clothFabricService.deleteCloth(namesDeleted);

        tulleController.seeAllTulle();

}
}
