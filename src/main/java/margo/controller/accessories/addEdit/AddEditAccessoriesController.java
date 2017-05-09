package margo.controller.accessories.addEdit;


import margo.controller.accessories.AccessoriesController;
import margo.model.modelDTO.accessories.AccessoriesDTO;
import margo.service.accessories.AccessoriesService;
import margo.service.accessories.AddEditAccessoriesService;
import margo.service.accessories.CheckAccessoriesRepositoryService;
import margo.service.interior.AddPatternForInterior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class AddEditAccessoriesController {


    @Autowired
    private AccessoriesController accessoriesController;
    @Autowired
    private AccessoriesService service;
    @Autowired
    private AddEditAccessoriesService addEditAccessoriesService;
    @Autowired
    private CheckAccessoriesRepositoryService repositoryService;

    private final String band = "band";
    private final String fringe = "fringe";
    private final String luvers = "luvers";
    private final String pickup = "pickup";
    private final String various = "various";

    @Value("${img.band.path}")
    private String realObjectsPathBend;
    @Value("${img.band.relative.path}")
    private String relativeObjectsPathBend;
    @Value("${img.fringe.path}")
    private String realObjectsPathFringe;
    @Value("${img.fringe.relative.path}")
    private String relativeObjectsPathFringe;
    @Value("${img.luvers.path}")
    private String realObjectsPathLuvers;
    @Value("${img.luvers.relative.path}")
    private String relativeObjectsPathLuvers;
    @Value("${img.pickup.path}")
    private String realObjectsPathPickup;
    @Value("${img.pickup.relative.path}")
    private String relativeObjectsPathPickup;
    @Value("${img.various.path}")
    private String realObjectsPathVarious;
    @Value("${img.various.relative.path}")
    private String relativeObjectsPathVarious;

    private String checkTo;

    @RequestMapping(value = "/addInfoAboutNewAccessories", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAddCurtain(@RequestParam(required = false) Long id,
                                          @RequestParam(value = "part", required = false)String part) {
        ModelAndView modelAndView = new ModelAndView();
        checkTo = part;
        if (id != null ) {
            AccessoriesDTO curtain = service.viewSelectedFinishProduct(id, repositoryService.selectRepository(part));
            curtain.setIdForEditCurtain(id);
            modelAndView.addObject("comparePhotoNameWithDB", curtain);
            modelAndView.setViewName("accessories/add/addEditAccessories");
            return modelAndView;
        } else {
            modelAndView.setViewName("accessories/add/addEditAccessories");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/addSuccessfulAccessories", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addInfoCars(@ModelAttribute("comparePhotoNameWithDB") AccessoriesDTO dto,
                                    BindingResult result) throws IOException {

        AddPatternForInterior addPattern = new AddPatternForInterior();
        if (checkTo.equals(band)) {
            addPattern.checkInformations(dto, realObjectsPathBend, relativeObjectsPathBend);
        } else if (checkTo.equals(fringe)){
            addPattern.checkInformations(dto, realObjectsPathFringe, relativeObjectsPathFringe);
        } else if (checkTo.equals(luvers)) {
            addPattern.checkInformations(dto, realObjectsPathLuvers, relativeObjectsPathLuvers);
        } else if (checkTo.equals(various)) {
            addPattern.checkInformations(dto, realObjectsPathVarious, relativeObjectsPathVarious);
        }else if (checkTo.equals(pickup)) {
            addPattern.checkInformations(dto, realObjectsPathPickup, relativeObjectsPathPickup);
        }

        if (dto.getIdForEditCurtain() != null ) {
            dto.setPhoto(addPattern.getNameFile());
            dto.setPhoto01(addPattern.getNameFile01());
            dto.setPhoto02(addPattern.getNameFile02());

            addEditAccessoriesService.editCurtain(dto, repositoryService.selectRepository(checkTo));
            return accessoriesController.showBedroom(checkTo);
        } else {
            try{
                addEditAccessoriesService.compareEnterInfoAndInDB(addPattern.getNameFile(), addPattern.getNameFile01(),
                        addPattern.getNameFile02(), dto.getName(), dto.getDescription(),dto.getColor(),
                        dto.getQuantity(), dto.getPrice(), checkTo);
                return accessoriesController.showBedroom(checkTo);
            } catch (RuntimeException r){

                result.rejectValue("name", "error.name", "Error: Name or Photo exist");
//            return seePageAddYachts(yachtDTO.getName());
                return viewException();
            }
            //если я получу ошибку между открытием и закрытием потока, то поток без finally не закроется
            finally {
                if (addPattern.getFileOutputStream() != null) {
                    addPattern.getFileOutputStream().close();
                }
            }
        }
    }
    public ModelAndView viewException() {
        ModelAndView andView = new ModelAndView();
        andView.setViewName("accessories/add/addEditAccessories");
        return andView;
    }
    @ModelAttribute("comparePhotoNameWithDB")
    public AccessoriesDTO createModel() {
        return new AccessoriesDTO();
    }
    public String getCheckTo() {
        return checkTo;
    }
}