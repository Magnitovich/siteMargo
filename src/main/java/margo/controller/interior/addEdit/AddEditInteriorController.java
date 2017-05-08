package margo.controller.interior.addEdit;


import margo.controller.interior.InteriorController;
import margo.model.modelDTO.interior.InteriorDTO;
import margo.service.interior.AddEditInteriorService;
import margo.service.interior.AddPatternForInterior;
import margo.service.interior.InteriorService;
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
public class AddEditInteriorController {

    @Autowired
    private InteriorController interiorController;
    @Autowired
    private AddEditInteriorService addEditInteriorService;
    @Autowired
    private InteriorService interiorService;

    @Value("${img.interior.path}")
    private String realObjectsPathInterior;
    @Value("${img.interior.relative.path}")
    private String relativeObjectsPathInterior;


    @RequestMapping(value = "/addInfoAboutNewInteriorProduct", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seePageAddCurtain(@RequestParam(required = false) Long id) {
        ModelAndView modelAndView = new ModelAndView();
        if (id != null) {
            InteriorDTO curtain = interiorService.viewSelectedFinishProduct(id);
            curtain.setIdForEditCurtain(id);
            modelAndView.addObject("comparePhotoNameWithDB", curtain);
            modelAndView.setViewName("interior/add/addEditInterior");
            return modelAndView;
        } else {
            modelAndView.setViewName("interior/add/addEditInterior");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/addSuccessfulInterior", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addInfoCars(@ModelAttribute("comparePhotoNameWithDB") InteriorDTO dto,
                                    BindingResult result) throws IOException {

        AddPatternForInterior addPattern = new AddPatternForInterior();
        addPattern.checkInformations(dto, realObjectsPathInterior, relativeObjectsPathInterior);

        if (dto.getIdForEditCurtain() != null) {
            dto.setPhoto(addPattern.getNameFile());
            dto.setPhoto01(addPattern.getNameFile01());
            dto.setPhoto02(addPattern.getNameFile02());

            interiorService.editCurtain(dto);
            return interiorController.seeInterior();
        } else {
            try {
                addEditInteriorService.compareEnterInfoAndInDB(addPattern.getNameFile(), addPattern.getNameFile01(),
                        addPattern.getNameFile02(), dto.getName(), dto.getDescription(),
                        dto.getColor(), dto.getQuantity(), dto.getPrice());
                return interiorController.seeInterior();
            } catch (RuntimeException r) {

                result.rejectValue("name", "error.name", "Error: Name or Photo exist");
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
        andView.setViewName("interior/add/addEditInterior");
        return andView;
    }

    @ModelAttribute("comparePhotoNameWithDB")
    public InteriorDTO createModel() {
        return new InteriorDTO();
    }
}

