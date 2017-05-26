package margo.controller.aboutFabric;

import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.model.modelDTO.allCurtainsDTO.CurtainDTO;
import margo.service.fabric.CurtainService;
import margo.service.finishedProduct.MainFinishedService;
import margo.service.offer.SelectRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CurtainController {

    @Autowired
    private MainFinishedService curtainService;
    @Autowired
    private SelectRepositoryService selectRepositoryService;

    private CrudRepository repository;

    @RequestMapping(value = "/curtainModels", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seeAllCurtain(@RequestParam(value = "part")String part){

//        paginationService.findAllRows();
        repository = selectRepositoryService.selectRepository(part);
        List<AllFabricDTO> curtainDTOs = curtainService.seeAllModels(repository);
        ArrayList colorModel =curtainService.seeColor();
        ArrayList paint =curtainService.seePaint();
        ArrayList structure =curtainService.seeStructure();
        ArrayList filterPrice = curtainService.seePrice();
//        for(CurtainDTO dto:curtainDTOs){
//            String k = dto.getDescription();
//            System.out.println("TextArea:  "+k);
//        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("namePage", part);
        modelAndView.addObject("allCurtain",curtainDTOs);
        modelAndView.addObject("price",filterPrice);
        modelAndView.addObject("forColor",colorModel);
        modelAndView.addObject("forPaint",paint);
        modelAndView.addObject("forStructure",structure);
        modelAndView.setViewName("allFabric/curtainModel");
        return modelAndView;
    }

    public CrudRepository getRepository() {
        return repository;
    }
}
